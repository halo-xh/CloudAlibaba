package com.xh.bootcore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author Xiao Hong
 * @since 2022-11-03
 */
@Component
public class BootAppRunner implements ApplicationListener, ApplicationContextAware, Ordered {

    private static final Logger log = LoggerFactory.getLogger(BootAppRunner.class);

    private ApplicationContext applicationContext;

    private void initialize() {
        long startTimeMillis = System.currentTimeMillis();
        Map<String, MyApplicationRunner> applicationRunnerMap = applicationContext.getBeansOfType(MyApplicationRunner.class);

        Map<Integer, Set<MyApplicationRunner>> applicationRunners = applicationRunnerMap.values().stream()
                .collect(Collectors.groupingBy(MyApplicationRunner::getOrder, TreeMap::new, Collectors.toSet()));

        for (Integer order : applicationRunners.keySet()) {
            Set<MyApplicationRunner> pendingRunners = applicationRunners.get(order);
            this.callMyApplicationRunners(order, pendingRunners);
        }

        log.info("MyApplicationRunner， 总耗时：{}毫秒", System.currentTimeMillis() - startTimeMillis);
    }

    private void callMyApplicationRunners(int order, Set<MyApplicationRunner> pendingRunners) {
        log.info("MyApplicationRunner[Order({})]开始执行 ...", order);
        long startMillis = System.currentTimeMillis();
        final AtomicBoolean isTerminal = new AtomicBoolean(false);
        final CountDownLatch latch = new CountDownLatch(pendingRunners.size());
        final String mainThreadName = Thread.currentThread().getName();
        final StringBuilder errorRunnerName = new StringBuilder();

        for (MyApplicationRunner runner : pendingRunners) {
            new Thread(() -> {
                String threadName = Thread.currentThread().getName();
                Thread.currentThread().setName(mainThreadName + "-" + runner.getClass().getSimpleName());
                long startTimeMillis = System.currentTimeMillis();

                try {
                    BootAppRunner.log.info("[{}]初始化开始... ", runner.getClass().getName());
                    runner.run();
                } catch (Throwable var8) {
                    BootAppRunner.log.error(var8.getMessage(), var8);
                    isTerminal.set(true);
                    errorRunnerName.append(",");
                    errorRunnerName.append(runner.getClass().getName());
                } finally {
                    latch.countDown();
                    BootAppRunner.log.info("[{}]初始化完成，耗时：{}毫秒", runner.getClass().getName(), System.currentTimeMillis() - startTimeMillis);
                    Thread.currentThread().setName(threadName);
                }

            }).start();
        }

        try {
            latch.await();
        } catch (Throwable var14) {
            log.error(var14.getMessage(), var14);
        } finally {
            log.info("MyApplicationRunner[Order({})]执行完成，耗时：{}毫秒", order, System.currentTimeMillis() - startMillis);
        }

        if (isTerminal.get()) {
            log.error("[{}]执行出错，导致启动失败！", errorRunnerName.substring(1));
        }

    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            this.initialize();
        }
    }


    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
