package com.example.cloudsimple.test;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Xiao Hong
 * @since 2022-06-15
 */
public class CompletableFutureTest {

    private static final ThreadPoolTaskExecutor poolTaskExecutor;

    static {
        poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setThreadNamePrefix("CompletableFutureThread");
        poolTaskExecutor.setCorePoolSize(10);
        poolTaskExecutor.setMaxPoolSize(30);
        poolTaskExecutor.setQueueCapacity(1000);
        poolTaskExecutor.setKeepAliveSeconds(5);
        poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        poolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        poolTaskExecutor.setAwaitTerminationSeconds(5);
        poolTaskExecutor.initialize();
    }

    public static void main(String[] args) throws Exception {
//        runAsync();
        supplyAsync();
//        thenAcceptBoth();
        poolTaskExecutor.shutdown();
    }

    //无返回值
    public static void runAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("sleep 结束。。。");
            } catch (InterruptedException e) {
                System.out.println("运行结束。。。");
            }
        }, poolTaskExecutor);
        future.get();
        System.out.println("future = " + future.isDone());
    }


    //无返回值
    public static void supplyAsync() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("sleep 结束。。。");
            } catch (InterruptedException e) {
                System.out.println("运行结束。。。");
            }
            return 1231;
        }, poolTaskExecutor);
        System.out.println(future.get());
    }


    private static void thenAcceptBoth() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + t);
            return t;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2=" + t);
            return t;
        });
        f1.thenAcceptBoth(f2, (t, u) -> System.out.println("f1=" + t + ";f2=" + u + ";"));
    }

}
