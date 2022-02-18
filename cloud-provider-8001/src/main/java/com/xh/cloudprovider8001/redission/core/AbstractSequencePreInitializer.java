package com.xh.cloudprovider8001.redission.core;

import com.xh.cloudprovider8001.redission.core.exception.GeneratorInitException;
import com.xh.cloudprovider8001.redission.core.model.SequenceDetail;
import com.xh.cloudprovider8001.redission.core.service.SequenceDetailService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaohong
 * @date 2022/2/17 10:08
 **/
@Slf4j
public abstract class AbstractSequencePreInitializer implements SequencePreInitializer {


    public boolean initIfNeed(Object seqKey) {
        SequenceDetail sequenceDetails = getSequenceDetailProvider().get(seqKey);
        if (needInit(sequenceDetails)) {
            if (acquireInitLock(sequenceDetails)) {
                try {
                    initSeqDetails(sequenceDetails);
                } finally {
                    releaseInitLock(sequenceDetails);
                }
            }
            return true;
        }
        return false;
    }

    protected abstract void releaseInitLock(SequenceDetail sequenceDetails);


    @Override
    public void initSeqDetails(SequenceDetail sequenceDetail) {
        try {
            if (saveSequenceDetails(sequenceDetail)) {
                doAfterInitialize(sequenceDetail);
            }
        } catch (Exception e) {
            log.error("[SequenceGenerator] 初始化数据失败 ", e);
            throw new GeneratorInitException("[SequenceGenerator] 初始化数据失败");
        }
    }

    protected void doAfterInitialize(SequenceDetail sequenceDetails) {
        log.info("[SequenceGenerator] 初始化完成 sequenceDetails:{}", sequenceDetails);
    }


    /**
     * 获取初始化锁
     *
     * @param sequenceDetails sequenceDetails
     * @return 获取锁的结果
     */
    protected abstract boolean acquireInitLock(SequenceDetail sequenceDetails);

    /**
     * 乐观判断
     *
     * @param sequenceDetail @return 查询结果
     */
    @Override
    public boolean needInit(SequenceDetail sequenceDetail) {
        String bizType = sequenceDetail.getBizType();
        SequenceDetail detail = getSequenceDetailService().findByBizType(bizType);
        return detail == null;
    }

    public boolean saveSequenceDetails(SequenceDetail sequenceDetail) {
        return getSequenceDetailService().createIfNotExist(sequenceDetail);
    }

    protected abstract SequenceDetailService getSequenceDetailService();

    protected abstract SequenceDetailProvider getSequenceDetailProvider();
}
