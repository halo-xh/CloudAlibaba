package com.xh.bootcore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Xiao Hong
 * @since 2022-11-03
 */
@Component
public class MyApplicationRunnerImpl2 implements MyApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(MyApplicationRunnerImpl2.class);


    @Override
    public void run() throws Exception {
        log.info("MyApplicationRunner2 run...");
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
