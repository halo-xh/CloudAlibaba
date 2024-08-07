package com.example.cloudsimple;

import com.example.cloudsimple.jetcache.SnowflakeIdWorker;
import com.example.cloudsimple.limit.RedisRateChecker;
import com.example.cloudsimple.test.TestEventListener;
import com.example.cloudsimple.test.TestEventListener2;
import com.example.id.IdCreator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@SpringBootTest
class CloudSimpleApplicationTests {

    @Autowired
    private TestEventListener eventListener;

    @Autowired
    private TestEventListener2 eventListener2;

    @Autowired
    private IdCreator idCreator;

    @Autowired
    private SnowflakeIdWorker worker;

    @Autowired
    private RedisRateChecker redisRateChecker;


    @Test
    void contextLoads() {
        eventListener.createTestDO();
    }

    @Test
    void contextLoads2() {
        eventListener2.createTestDO();
    }


    @SneakyThrows
    @Test
    void setIdCreator() {

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                Long shortID = idCreator.getShortID();
                log.info("shortID = " + shortID);
                long sn = worker.nextId();
                log.info("sn = " + sn);
            }).start();
        }
        TimeUnit.SECONDS.sleep(200);
    }

    @SneakyThrows
    @Test
    void testRate() {

        LocalDateTime now = LocalDateTime.now();
        AtomicInteger atomicInteger = new AtomicInteger();
        new Thread(() -> {
            while (true) {
                System.out.println(LocalDateTime.now() + ":" + Duration.between(now, LocalDateTime.now()).toMillis() + " passed: " + atomicInteger.get());
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        for (int i = 0; i < 30000; i++) {
            boolean key_r = redisRateChecker.check("key_r", 300, 5L);
            if (key_r) {
                int i1 = atomicInteger.incrementAndGet();
                System.out.println(" passed: " + Thread.currentThread());
            }
        }

        TimeUnit.SECONDS.sleep(4000);

    }
}
