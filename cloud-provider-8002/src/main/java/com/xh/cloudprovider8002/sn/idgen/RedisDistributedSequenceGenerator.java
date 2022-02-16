package com.xh.cloudprovider8002.sn.idgen;

import com.xh.cloudprovider8002.sn.idgen.exception.SequenceUsedUpException;
import com.xh.cloudprovider8002.sn.idgen.model.RedisFakeSequenceAcquireLock;
import com.xh.cloudprovider8002.sn.idgen.model.Sequence;
import com.xh.cloudprovider8002.sn.idgen.model.SequenceAcquireLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author xiaohong
 * @date 2022/2/16 13:12
 **/
@Slf4j
public class RedisDistributedSequenceGenerator extends AbstractDistributedSequenceGenerator {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisDistributedSequenceGenerator(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * @param seqKey bizType
     * @return fake lock.
     */
    @Override
    protected SequenceAcquireLock lock(Object seqKey) {
        String bizType = (String) seqKey;
        String max = redisTemplate.opsForValue().get(maxKeyHelper(bizType));
        if (max != null) {
            RedisFakeSequenceAcquireLock lock = new RedisFakeSequenceAcquireLock();
            lock.setLocked(true);
            lock.setBizType(bizType);
            lock.setMax(Long.valueOf(max));
            log.info("[SequenceGenerator-redis] 获取锁成功 bizType:{}, max:{}", bizType, max);
            return lock;
        }
        return null;
    }

    @Override
    Sequence generate(SequenceAcquireLock lock) {
        RedisFakeSequenceAcquireLock fakeSequenceAcquireLock = (RedisFakeSequenceAcquireLock) lock;
        String bizType = fakeSequenceAcquireLock.getBizType();
        Long increment = redisTemplate.opsForValue().increment(seqKeyHelper(bizType));
        log.info("[SequenceGenerator-redis] 创建sequence bizType:{}, sequence:{}", bizType, increment);
        if (increment != null && increment > fakeSequenceAcquireLock.getMax()) {
            log.error("[SequenceGenerator] bizType:{} 序列号已用完", bizType);
            throw new SequenceUsedUpException(String.format("bizType: %s 序列号已用完", bizType));
        }
        return new Sequence(bizType, increment);
    }

    @Override
    void unLock(SequenceAcquireLock lock) {
    }

    private String seqKeyHelper(String bizType) {
        return "ds:seq:" + bizType;
    }

    private String maxKeyHelper(String bizType) {
        return "ds:max:" + bizType;
    }
}
