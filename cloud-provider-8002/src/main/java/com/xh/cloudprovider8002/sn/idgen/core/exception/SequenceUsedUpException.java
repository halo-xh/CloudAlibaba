package com.xh.cloudprovider8002.sn.idgen.core.exception;

/**
 * seq用完异常
 *
 * @author xiaohong
 * @date 2022/2/16 17:26
 **/
public class SequenceUsedUpException extends RuntimeException {

    public SequenceUsedUpException(String message) {
        super(message);
    }

}
