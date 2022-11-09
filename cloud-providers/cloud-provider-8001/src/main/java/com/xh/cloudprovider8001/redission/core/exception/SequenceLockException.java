package com.xh.cloudprovider8001.redission.core.exception;

/**
 * 获取锁失败异常
 *
 * @author xiaohong
 * @date 2022/2/18 13:44
 **/
public class SequenceLockException extends RuntimeException {

    public SequenceLockException(String message) {
        super(message);
    }

}
