package com.xh.cloudprovider8002.sn.idgen;

import com.xh.cloudprovider8002.sn.idgen.model.Sequence;
import com.xh.cloudprovider8002.sn.idgen.model.SequenceAcquireLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaohong
 * @date 2022/2/16 12:16
 **/
@Slf4j
public abstract class AbstractDistributedSequenceGenerator implements SequenceGenerator {


    /**
     * 获取分布式锁、阻塞.
     *
     * @return
     * @param seqKey
     */
    protected abstract SequenceAcquireLock lock(Object seqKey);

    @Override
    public Sequence nextSequence(Object seqKey) {
        SequenceAcquireLock lock = lock(seqKey);
        Sequence sequence = null;
        if (lock.isLocked()) {
            try {
                sequence = generate(lock);
            } catch (Exception e) {
                log.error("[SequenceGenerator] 生成 sequence 失败 seqKey:{}", seqKey, e);
            } finally {
                unLock(lock);
            }
        }
        return sequence;
    }

    /**
     * 生成 sequence
     *
     * @param lock 帮助生成的key
     * @return sequence
     */
    abstract Sequence generate(SequenceAcquireLock lock);

    abstract void unLock(SequenceAcquireLock lock);

}
