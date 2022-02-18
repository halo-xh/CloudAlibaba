package com.xh.cloudprovider8001.redission.designresource;

import com.xh.cloudprovider8001.redission.core.model.Sequence;
import lombok.Data;

/**
 * @author xiaohong
 * @date 2022/2/16 10:56
 **/
@Data
public class DesignResourceSequence {

    /**
     * 不足4位,向前补0.
     */
    private static final String PATTERN_04D = "%04d";

    final Sequence sequence;

    final String suffix;

    public String result() {
        return sequence.getBizType() + String.format(PATTERN_04D, sequence.getCurrentVal()) + getSuffix();
    }


}
