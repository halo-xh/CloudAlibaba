package com.xh.cloudprovider8002.sn.idgen.core;

import com.xh.cloudprovider8002.sn.idgen.core.exception.SequenceLockException;
import com.xh.cloudprovider8002.sn.idgen.core.model.Sequence;
import com.xh.cloudprovider8002.sn.idgen.core.model.SequenceAcquireLock;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonLockEntry;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaohong
 * @date 2022/2/16 12:16
 **/
@Slf4j
public abstract class AbstractDistributedSequenceGenerator extends AbstractSequencePreInitializer implements SequenceGenerator {


    /**
     * 生成序列。
     *
     * @param seqKey seqKey
     * @return Sequence
     */
    @Override
    public Sequence nextSequence(Object seqKey) {
        SequenceAcquireLock lock = lock(seqKey);
        if (lock == null) {
            if (initIfNeed(seqKey)) {
                // try lock again.
                for (int i = 0; i < 3; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock = lock(seqKey);
                    if (lock != null && lock.isLocked()) {
                        return getSequence(seqKey, lock);
                    }
                }
            }
        } else {
            if (lock.isLocked()) {
                return getSequence(seqKey, lock);
            }
        }
        // lock failed.
        log.error("[SequenceGenerator] lock failed. seqKey:{}", seqKey);
        throw new SequenceLockException("[SequenceGenerator] lock failed.");
    }

    public Sequence getSequence(Object seqKey, SequenceAcquireLock lock) {
        try {
            return generate(lock);
        } catch (Exception e) {
            log.error("[SequenceGenerator] generate sequence failed. seqKey:{}", seqKey, e);
            throw e;
        } finally {
            unLock(lock);
        }
    }


    /**
     * 生成 sequence
     *
     * @param lock 帮助生成的key
     * @return sequence
     */
    protected abstract Sequence generate(SequenceAcquireLock lock);

    /**
     * 获取分布式锁、阻塞.
     */
    protected abstract SequenceAcquireLock lock(Object seqKey);


    protected abstract void unLock(SequenceAcquireLock lock);


}
