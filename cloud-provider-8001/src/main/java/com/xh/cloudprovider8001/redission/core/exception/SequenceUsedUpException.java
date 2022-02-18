package com.xh.cloudprovider8001.redission.core.exception;

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
