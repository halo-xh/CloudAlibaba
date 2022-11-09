package com.xh.cloudprovider8001.redission.core;

import com.xh.cloudprovider8001.redission.core.model.Sequence;
import org.springframework.lang.Nullable;

/**
 * @author xiaohong
 * @date 2022/2/16 10:35
 **/
public interface SequenceGenerator {


    /**
     * 获取sequence
     */
    Sequence nextSequence(@Nullable Object seqKey);

}
