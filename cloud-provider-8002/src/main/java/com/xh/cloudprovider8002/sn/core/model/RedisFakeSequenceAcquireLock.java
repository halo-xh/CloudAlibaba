package com.xh.cloudprovider8002.sn.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author xiaohong
 * @date 2022/2/16 17:30
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class RedisFakeSequenceAcquireLock extends SequenceAcquireLock {

    private Long max;

    private String bizType;

}
