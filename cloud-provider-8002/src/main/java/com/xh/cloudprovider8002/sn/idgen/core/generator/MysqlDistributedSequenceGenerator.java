package com.xh.cloudprovider8002.sn.idgen.core.generator;


import com.google.common.base.Preconditions;
import com.xh.cloudprovider8002.sn.idgen.core.AbstractDistributedSequenceGenerator;
import com.xh.cloudprovider8002.sn.idgen.core.SeqConstants;
import com.xh.cloudprovider8002.sn.idgen.core.exception.SequenceUsedUpException;
import com.xh.cloudprovider8002.sn.idgen.core.model.Sequence;
import com.xh.cloudprovider8002.sn.idgen.core.model.SequenceAcquireLock;
import com.xh.cloudprovider8002.sn.idgen.core.model.SequenceDetail;
import com.xh.cloudprovider8002.sn.idgen.core.service.SequenceDetailService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaohong
 * @date 2022/2/16 13:53
 **/
@Slf4j
public abstract class MysqlDistributedSequenceGenerator extends AbstractDistributedSequenceGenerator {

    private final SequenceDetailService sequenceDetailService;

    public MysqlDistributedSequenceGenerator(SequenceDetailService sequenceDetailService) {
        this.sequenceDetailService = sequenceDetailService;
    }

    @Override
    public Sequence nextSequence(Object seqKey) {
        return super.nextSequence(seqKey);
    }


    @Override
    protected SequenceAcquireLock lock(Object seqKey) {
        SequenceDetail snGenerate = sequenceDetailService.lockForBizType((String) seqKey);
        if (snGenerate != null) {
            log.info("[SequenceGenerator-mysql] get lock bizType:{}, current:{}, Version:{}", snGenerate.getBizType(), snGenerate.getCurrent(), snGenerate.getVersion());
            return new SequenceAcquireLock(true, snGenerate);
        }
        return null;
    }

    @Override
    protected Sequence generate(SequenceAcquireLock lock) {
        SequenceDetail detail = lock.getSequenceDetail();
        String bizType = detail.getBizType();
        Long current = detail.getCurrent() + detail.getStep();
        if (current > detail.getMaxVal()) {
            log.error("[SequenceGenerator] bizType:{} 序列号已用完", bizType);
            throw new SequenceUsedUpException(String.format("bizType: %s 序列号已用完", bizType));
        }
        log.info("[SequenceGenerator-redis] 创建sequence bizType:{}, sequence:{}", bizType, current);
        Sequence sequence = new Sequence(bizType, current);
        int stock = sequenceDetailService.increaseCurrentWithLockVersion(bizType, current, detail.getVersion());
        log.info("[SequenceGenerator-mysql] 更新记录bizType:{}, current:{}, Version:{}", bizType, current, detail.getVersion());
        Preconditions.checkState(stock != 0, "更新失败");
        return sequence;
    }


    /**
     *
     * @param sequenceDetails sequenceDetails
     * @return
     */
    @Override
    protected boolean getInitLock(SequenceDetail sequenceDetails) {
        SequenceDetail detail = sequenceDetailService.lockForBizType(SeqConstants.MYSQL_LOCK_BIZ_TYPE);
        return detail != null;
    }

    @Override
    protected SequenceDetailService getSequenceDetailService() {
        return sequenceDetailService;
    }


    /**
     * 事务结束自动释放行锁
     *
     * @param lock lock
     */
    @Override
    protected void unLock(SequenceAcquireLock lock) {
    }
}
