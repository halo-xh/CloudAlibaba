package com.xh.cloudprovider8002.sn.core.model;

/**
 * @author xiaohong
 * @date 2022/2/16 10:41
 **/
public class Sequence {

    private final String bizType;

    private final Long currentVal;

    public Sequence(String bizType, Long currentVal) {
        this.bizType = bizType;
        this.currentVal = currentVal;
    }

    public String getBizType() {
        return bizType;
    }

    public Long getCurrentVal() {
        return currentVal;
    }
}
