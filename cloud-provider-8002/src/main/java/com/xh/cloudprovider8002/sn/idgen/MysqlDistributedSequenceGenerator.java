package com.xh.cloudprovider8002.sn.idgen;


import com.google.common.base.Preconditions;
import com.xh.cloudprovider8002.sn.entity.CmSnGenerate;
import com.xh.cloudprovider8002.sn.idgen.model.Sequence;
import com.xh.cloudprovider8002.sn.idgen.model.SequenceAcquireLock;
import com.xh.cloudprovider8002.sn.service.ICmSnGenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaohong
 * @date 2022/2/16 13:53
 **/
@Slf4j
public class MysqlDistributedSequenceGenerator extends AbstractDistributedSequenceGenerator {

    private final ICmSnGenerateService cmSnGenerateService;

    public MysqlDistributedSequenceGenerator(ICmSnGenerateService cmSnGenerateService) {
        this.cmSnGenerateService = cmSnGenerateService;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Sequence nextSequence(Object seqKey) {
        return super.nextSequence(seqKey);
    }

    @Override
    protected SequenceAcquireLock lock(Object seqKey) {
        CmSnGenerate snGenerate = cmSnGenerateService.selectForUpdate((String) seqKey);
        if (snGenerate != null) {
            log.info("[SequenceGenerator-x] 得到锁 bizType:{},current:{},Version:{}", snGenerate.getBizType(), snGenerate.getCurrent(), snGenerate.getVersion());
            snGenerate.setLocked(true);
        }
        return snGenerate;
    }

    @Override
    protected Sequence generate(SequenceAcquireLock seqKey) {
        CmSnGenerate lock = (CmSnGenerate) seqKey;
        String bizType = lock.getBizType();
        Long current = lock.getCurrent() + lock.getStep();
        if (current > lock.getMax()) {
            log.error("[SequenceGenerator] bizType:{} 序列号已用完", bizType);
        }
        Preconditions.checkState(current <= lock.getMax(), "序列号已用完");
        Sequence sequence = new Sequence(bizType, currentVal);
        sequence.setBizType(bizType);
        sequence.setCurrentVal(current);
        log.info("[SequenceGenerator-x] 更新记录bizType:{},current:{},Version:{}", bizType, current, lock.getVersion());
        int stock = cmSnGenerateService.updateStock(bizType, current, lock.getVersion());
        Preconditions.checkState(stock != 0, "更新失败");
        return sequence;
    }

    @Override
    void unLock(SequenceAcquireLock lock) {
    }
}
