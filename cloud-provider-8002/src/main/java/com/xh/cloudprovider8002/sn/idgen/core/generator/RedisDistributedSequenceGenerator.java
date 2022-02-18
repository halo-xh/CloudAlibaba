package com.xh.cloudprovider8002.sn.idgen.core.generator;

import com.xh.cloudprovider8002.sn.idgen.core.AbstractDistributedSequenceGenerator;
import com.xh.cloudprovider8002.sn.idgen.core.SeqConstants;
import com.xh.cloudprovider8002.sn.idgen.core.exception.SequenceUsedUpException;
import com.xh.cloudprovider8002.sn.idgen.core.model.RedisFakeSequenceAcquireLock;
import com.xh.cloudprovider8002.sn.idgen.core.model.Sequence;
import com.xh.cloudprovider8002.sn.idgen.core.model.SequenceAcquireLock;
import com.xh.cloudprovider8002.sn.idgen.core.model.SequenceDetail;
import com.xh.cloudprovider8002.sn.idgen.core.service.SequenceDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author xiaohong
 * @date 2022/2/16 13:12
 **/
@Slf4j
public abstract class RedisDistributedSequenceGenerator extends AbstractDistributedSequenceGenerator {

    private final RedisTemplate<String, String> redisTemplate;

    private final SequenceDetailService sequenceDetailService;


    public RedisDistributedSequenceGenerator(RedisTemplate<String, String> redisTemplate, SequenceDetailService sequenceDetailService) {
        this.redisTemplate = redisTemplate;
        this.sequenceDetailService = sequenceDetailService;
        checkCacheConsistent();
    }

    /**
     * 检查缓存一致性
     */
    protected void checkCacheConsistent() {
        log.info("[SequenceGenerator-redis] start init...");
        List<String> cachedBizTypeList = getCachedBizTypeList();
        if (cachedBizTypeList != null && !cachedBizTypeList.isEmpty() ) {
            checkSequenceUsedConsistent(cachedBizTypeList);
        }
    }

    protected void checkSequenceUsedConsistent(List<String> cachedBizTypeList) {
        log.info("[SequenceGenerator-redis] start check Sequence Consistent...");
        List<SequenceDetail> sequenceDetailList = sequenceDetailService.findAllByBizTypeList(cachedBizTypeList);
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            for (SequenceDetail detail : sequenceDetailList) {
                StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
                String bizType = detail.getBizType();
                String cacheSeqKey = seqKey(bizType);
                String cachedCurrentVal = stringRedisConnection.get(cacheSeqKey);
                Long current = detail.getCurrent();
                if (StringUtils.hasText(cachedCurrentVal)) {
                    long cachedCurrent = Long.parseLong(cachedCurrentVal);
                    if (current > cachedCurrent) {
                        // db 大于 redis
                        log.warn("[SequenceGenerator-redis] Consistent Conflict bizType:{} database:{} redis:{}. fix with max one.", bizType, current, cachedCurrentVal);
                        stringRedisConnection.set(cacheSeqKey, String.valueOf(current),
                                Expiration.from(3L, TimeUnit.DAYS), RedisStringCommands.SetOption.SET_IF_ABSENT);
                    } else if (current < cachedCurrent) {
                        // redis 大于 db
                        log.warn("[SequenceGenerator-redis] Consistent Conflict bizType:{} database:{} redis:{}. fix with max one.", bizType, current, cachedCurrentVal);
                        int updateStock = sequenceDetailService.increaseCurrentWithLockVersion(bizType, cachedCurrent, detail.getVersion());
                        if (updateStock < 1) {
                            log.error("[SequenceGenerator-redis] Consistent Conflict fix failed bizType:{} database:{} redis:{}.", bizType, current, cachedCurrentVal);
                        }
                    }
                }
            }
            return null;
        });
    }

    protected List<String> getCachedBizTypeList() {
        Set<String> seqCache = redisTemplate.keys(SeqConstants.REDIS_KEY_SEQ_VAL_PREFIX + "*");
        if (seqCache != null) {
            return seqCache.stream().map(k -> k.replaceFirst(SeqConstants.REDIS_KEY_SEQ_VAL_PREFIX, "")).collect(Collectors.toList());
        }
        return null;
    }


    /**
     * @param seqKey bizType
     * @return fake lock.
     */
    @Override
    protected SequenceAcquireLock lock(Object seqKey) {
        String bizType = (String) seqKey;
        String max = redisTemplate.opsForValue().get(maxKey(bizType));
        if (max != null) {
            RedisFakeSequenceAcquireLock lock = new RedisFakeSequenceAcquireLock();
            lock.setLocked(true);
            lock.setBizType(bizType);
            lock.setMax(Long.valueOf(max));
            log.info("[SequenceGenerator-redis] get lock bizType:{}, max:{}", bizType, max);
            return lock;
        }
        return null;
    }


    @Override
    protected Sequence generate(SequenceAcquireLock lock) {
        RedisFakeSequenceAcquireLock fakeSequenceAcquireLock = (RedisFakeSequenceAcquireLock) lock;
        String bizType = fakeSequenceAcquireLock.getBizType();
        Long increment = redisTemplate.opsForValue().increment(seqKey(bizType));
        log.info("[SequenceGenerator-redis] 创建sequence bizType:{}, sequence:{}", bizType, increment);
        if (increment != null && increment > fakeSequenceAcquireLock.getMax()) {
            log.error("[SequenceGenerator] bizType:{} 序列号已用完", bizType);
            throw new SequenceUsedUpException(String.format("bizType: %s 序列号已用完", bizType));
        }
        syncCache(bizType);
        return new Sequence(bizType, increment);
    }

    private void syncCache(String bizType) {
        sequenceDetailService.increaseCurrent(bizType);
    }


    /**
     * 将数据缓存到redis
     *
     * @param sequenceDetails sequenceDetails
     */
    @Override
    protected void doAfterInitialize(SequenceDetail sequenceDetails) {
        super.doAfterInitialize(sequenceDetails);
        String bizType = sequenceDetails.getBizType();
        redisTemplate.opsForValue().setIfAbsent(SeqConstants.REDIS_KEY_SEQ_VAL_PREFIX + bizType, String.valueOf(sequenceDetails.getCurrent()), 3, TimeUnit.DAYS);
        redisTemplate.opsForValue().setIfAbsent(SeqConstants.REDIS_KEY_MAX_VAL_PREFIX + bizType, String.valueOf(sequenceDetails.getMaxVal()), 3, TimeUnit.DAYS);
    }


    @Override
    protected boolean getInitLock(SequenceDetail sequenceDetails) {
        String bizType = sequenceDetails.getBizType();
        Boolean absent = redisTemplate.opsForValue().setIfAbsent(SeqConstants.REDIS_KEY_SEQ_INIT_PREFIX + bizType, "1", 10, TimeUnit.SECONDS);
        return (absent != null && absent);
    }


    @Override
    protected void unLock(SequenceAcquireLock lock) {
    }

    private String seqKey(String bizType) {
        return SeqConstants.REDIS_KEY_SEQ_VAL_PREFIX + bizType;
    }

    private String maxKey(String bizType) {
        return SeqConstants.REDIS_KEY_MAX_VAL_PREFIX + bizType;
    }

    @Override
    protected SequenceDetailService getSequenceDetailService() {
        return sequenceDetailService;
    }

}
