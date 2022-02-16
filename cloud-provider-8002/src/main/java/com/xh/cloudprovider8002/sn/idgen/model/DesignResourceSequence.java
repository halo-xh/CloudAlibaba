package com.xh.cloudprovider8002.sn.idgen.model;

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

    final String chargeInfo;

    public String getSeq() {
        if (sequence == null) {
            return "sequence 用完";
        }
        return sequence.getBizType() + String.format(PATTERN_04D, sequence.getCurrentVal()) + getChargeInfo();
    }


}
