package com.xh.bootcore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Xiao Hong
 * @since 2022-11-03
 */
@Component
public class MyApplicationRunnerImpl1 implements MyApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(MyApplicationRunnerImpl1.class);

    @Override
    public void run() throws Exception {
        log.info("MyApplicationRunner1 run...");
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
