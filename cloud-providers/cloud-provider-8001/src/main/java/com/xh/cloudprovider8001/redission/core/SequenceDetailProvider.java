package com.xh.cloudprovider8001.redission.core;


import com.xh.cloudprovider8001.redission.core.model.SequenceDetail;

/**
 * @author xiaohong
 * @date 2022/2/17 14:01
 **/
public interface SequenceDetailProvider {

    /**
     * 根据key获取详情
     */
    SequenceDetail get(Object seqKey);
}
