package com.example.cloudsimple;

import com.example.cloudsimple.jetcache.SnowflakeIdWorker;
import com.example.cloudsimple.test.TestEventListener;
import com.example.cloudsimple.test.TestEventListener2;
import com.example.id.IdCreator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;


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
            new Thread(() ->{
                Long shortID = idCreator.getShortID();
                log.info("shortID = " + shortID);
                long sn = worker.nextId();
                log.info("sn = " + sn);
            }).start();
        }
        TimeUnit.SECONDS.sleep(200);


    }
}
