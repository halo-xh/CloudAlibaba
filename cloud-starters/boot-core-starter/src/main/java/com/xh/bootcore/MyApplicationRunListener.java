package com.xh.bootcore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

import java.time.Duration;

/**
 * @author Xiao Hong
 * @since 2022-11-03
 */
public class MyApplicationRunListener  implements SpringApplicationRunListener, Ordered {
    private static final Logger log = LoggerFactory.getLogger(MyApplicationRunListener.class);


    public MyApplicationRunListener(SpringApplication sa, String[] args) {
        log.info("MyApplicationRunListener cons ");

    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        log.info("environmentPrepared===>");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("contextPrepared===>");

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("contextLoaded===>");

    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        log.info("started===>");

    }

    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        log.info("MyApplicationRunListener ready===> ");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("MyApplicationRunListener failed===> ");
    }
}
