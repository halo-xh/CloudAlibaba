package com.xh.cloudprovider8001.metrix;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Xiao Hong
 * @since 2022-11-07
 */
@Component
public class ThreadPoolMetric implements InitializingBean {

    private final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    private ObjectProvider<ThreadPoolTaskExecutor> threadPoolExecutors;

    private Runnable monitor = () -> {
        for (ThreadPoolTaskExecutor executor : threadPoolExecutors) {
            //这里需要捕获异常,尽管实际上不会产生异常,但是必须预防异常导致调度线程池线程失效的问题
            try {
                Iterable<Tag> tags = Collections.singletonList(Tag.of("thread.pool.name", executor.getThreadNamePrefix()));
                Metrics.gauge("thread.pool.core.size", tags, executor, ThreadPoolTaskExecutor::getCorePoolSize);
                Metrics.gauge("thread.pool.max.size", tags, executor, ThreadPoolTaskExecutor::getMaxPoolSize);
                Metrics.gauge("thread.pool.active.size", tags, executor, ThreadPoolTaskExecutor::getActiveCount);
                Metrics.gauge("thread.pool.thread.count", tags, executor, ThreadPoolTaskExecutor::getPoolSize);
                // 注意如果阻塞队列使用无界队列这里不能直接取size
                Metrics.gauge("thread.pool.queue.size", tags, executor, e -> e.getThreadPoolExecutor().getQueue().size());
            } catch (Exception e) {
                //ignore
            }
        }
    };

    @Override
    public void afterPropertiesSet() throws Exception {
        scheduledExecutor.scheduleAtFixedRate(monitor, 0, 5, TimeUnit.SECONDS);
    }
}
