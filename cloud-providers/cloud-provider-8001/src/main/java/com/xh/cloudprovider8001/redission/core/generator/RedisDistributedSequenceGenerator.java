package com.xh.cloudprovider8001.redission.core.generator;

import com.xh.cloudprovider8001.redission.core.AbstractDistributedSequenceGenerator;
import com.xh.cloudprovider8001.redission.core.SeqConstants;
import com.xh.cloudprovider8001.redission.core.exception.SequenceUsedUpException;
import com.xh.cloudprovider8001.redission.core.model.RedisFakeSequenceAcquireLock;
import com.xh.cloudprovider8001.redission.core.model.Sequence;
import com.xh.cloudprovider8001.redission.core.model.SequenceAcquireLock;
import com.xh.cloudprovider8001.redission.core.model.SequenceDetail;
import com.xh.cloudprovider8001.redission.core.service.SequenceDetailService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.client.codec.LongCodec;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaohong
 * @date 2022/2/16 13:12
 **/
@Slf4j
public abstract class RedisDistributedSequenceGenerator extends AbstractDistributedSequenceGenerator {

    private final RedissonClient redissonClient;

    private final SequenceDetailService sequenceDetailService;


    public RedisDistributedSequenceGenerator(RedissonClient redissonClient, SequenceDetailService sequenceDetailService) {
        this.redissonClient = redissonClient;
        this.sequenceDetailService = sequenceDetailService;
        checkCacheConsistent();
    }

    /**
     * 检查缓存一致性
     */
    protected void checkCacheConsistent() {
        log.info("[SequenceGenerator-redis] start init...");
        List<String> cachedBizTypeList = getCachedBizTypeList();
        if (cachedBizTypeList != null && !cachedBizTypeList.isEmpty()) {
            checkSequenceUsedConsistent(cachedBizTypeList);
        }
    }

    protected void checkSequenceUsedConsistent(List<String> cachedBizTypeList) {
        log.info("[SequenceGenerator-redis] start check Sequence Consistent...");
        List<SequenceDetail> sequenceDetailList = sequenceDetailService.findAllByBizTypeList(cachedBizTypeList);
        for (SequenceDetail detail : sequenceDetailList) {
            String bizType = detail.getBizType();
            String cacheSeqKey = seqKey(bizType);
            RBucket<Long> bucket = redissonClient.getBucket(cacheSeqKey, new LongCodec());
            Long cachedCurrent = bucket.get();
            Long current = detail.getCurrent();
            if (cachedCurrent != null) {
                if (current > cachedCurrent) {
                    // db 大于 redis
                    log.warn("[SequenceGenerator-redis] Consistent Conflict bizType:{} database:{} redis:{}. fix with max one.", bizType, current, cachedCurrent);
                    bucket.compareAndSet(cachedCurrent, current);
                } else if (current < cachedCurrent) {
                    // redis 大于 db
                    log.warn("[SequenceGenerator-redis] Consistent Conflict bizType:{} database:{} redis:{}. fix with max one.", bizType, current, cachedCurrent);
                    int updateStock = sequenceDetailService.increaseCurrentWithLockVersion(bizType, cachedCurrent, detail.getVersion());
                    if (updateStock < 1) {
                        log.error("[SequenceGenerator-redis] Consistent Conflict fix failed bizType:{} database:{} redis:{}.", bizType, current, cachedCurrent);
                    }
                }
            }
        }
    }

    protected List<String> getCachedBizTypeList() {
        RKeys keys = redissonClient.getKeys();
        Iterable<String> keysByPattern = keys.getKeysByPattern(SeqConstants.REDIS_KEY_SEQ_VAL_PREFIX + "*");
        if (keysByPattern != null) {
            ArrayList<String> cachedKeys = new ArrayList<>();
            keysByPattern.forEach(k -> cachedKeys.add(k.replaceFirst(SeqConstants.REDIS_KEY_SEQ_VAL_PREFIX, "")));
            return cachedKeys;
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
        RBucket<Long> max = redissonClient.getBucket(maxKey(bizType), new LongCodec());
        if (max.get() != null) {
            RedisFakeSequenceAcquireLock lock = new RedisFakeSequenceAcquireLock();
            lock.setLocked(true);
            lock.setBizType(bizType);
            lock.setMax(max.get());
            log.info("[SequenceGenerator-redis] get lock bizType:{}, max:{}", bizType, max);
            return lock;
        }
        return null;
    }


    @Override
    protected Sequence generate(SequenceAcquireLock lock) {
        RedisFakeSequenceAcquireLock fakeSequenceAcquireLock = (RedisFakeSequenceAcquireLock) lock;
        String bizType = fakeSequenceAcquireLock.getBizType();
        RAtomicLong atomicLong = redissonClient.getAtomicLong(seqKey(bizType));
        long increment = atomicLong.incrementAndGet();
        log.info("[SequenceGenerator-redis] 创建sequence bizType:{}, sequence:{}", bizType, increment);
        if (increment > fakeSequenceAcquireLock.getMax()) {
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
        RTransaction transaction = redissonClient.createTransaction(TransactionOptions.defaults());
        RBucket<Long> seq = transaction.getBucket(SeqConstants.REDIS_KEY_SEQ_VAL_PREFIX + bizType, new LongCodec());
        seq.set(sequenceDetails.getCurrent(), 3, TimeUnit.DAYS);
        RBucket<Long> max = transaction.getBucket(SeqConstants.REDIS_KEY_MAX_VAL_PREFIX + bizType, new LongCodec());
        max.set(sequenceDetails.getMaxVal(), 3, TimeUnit.DAYS);
        transaction.commit();
    }


    /**
     * 可重入锁
     */
    @Override
    protected boolean acquireInitLock(SequenceDetail sequenceDetails) {
        String bizType = sequenceDetails.getBizType();
        RLock lock = redissonClient.getLock(SeqConstants.REDIS_KEY_SEQ_INIT_PREFIX + bizType);
        return lock.tryLock();
    }

    @Override
    protected void releaseInitLock(SequenceDetail sequenceDetails) {
        RLock lock = redissonClient.getLock(SeqConstants.REDIS_KEY_SEQ_INIT_PREFIX + sequenceDetails.getBizType());
        lock.unlock();
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
