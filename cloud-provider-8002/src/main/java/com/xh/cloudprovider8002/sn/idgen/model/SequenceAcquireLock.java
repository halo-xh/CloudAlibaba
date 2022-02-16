package com.xh.cloudprovider8002.sn.idgen.model;

import lombok.Data;

/**
 * @author xiaohong
 * @date 2022/2/16 12:24
 **/
@Data
public class SequenceAcquireLock {

    private boolean locked;

    private String msg;


}
