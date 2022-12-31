package com.xh.cloudconfig9001.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaohong
 * @date 2022/2/12 11:01
 **/
@Slf4j
@Service
public class TestConsumeTopic {


    @StreamListener(value = Sink.INPUT)
    public void receive(GenericMessage<String> message) throws InterruptedException {
        String payload = message.getPayload();
        log.info(Thread.currentThread().getName() + " -> message:" + payload);
//        if (new Random().nextInt() % 3 ==0){
//            TimeUnit.SECONDS.sleep(20);
//        }
        log.info(Thread.currentThread().getName() + " -> end:");
    }


}
