package com.xh.cloudprovider8002.sn.idgen.core;


import com.xh.cloudprovider8002.sn.idgen.core.model.SequenceDetail;

/**
 * @author xiaohong
 * @date 2022/2/17 10:00
 **/
public interface SequencePreInitializer {


    /**
     * 判断是否需要初始化
     *
     * @param sequenceDetail 序列详情
     * @return res
     */
    boolean needInit(SequenceDetail sequenceDetail);

    /**
     * 初始化目标序列
     *
     * @param sequenceDetail target
     */
    void initSeqDetails(SequenceDetail sequenceDetail);

}
