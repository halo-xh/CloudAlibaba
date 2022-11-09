package com.xh.bootcore;

/**
 * @author Xiao Hong
 * @since 2022-11-03
 */
public interface MyApplicationRunner {

    void run() throws Exception;

    default int getOrder() {
        return 0;
    }
}
