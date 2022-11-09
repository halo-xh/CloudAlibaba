package com.xh.cloudprovider8001.redission.core.exception;

/**
 * 初始化失败异常
 *
 * @author xiaohong
 * @date 2022/2/17 9:56
 **/
public class GeneratorInitException extends RuntimeException {

    public GeneratorInitException(String message) {
        super(message);
    }

}
