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

/**
 * @author xiaohong
 * @date 2022/2/12 11:01
 **/
@Slf4j
@Service
public class TestConsumeTopic {


    @StreamListener(value = Sink.INPUT)
    public void receive(GenericMessage<String> message) throws InterruptedException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String payload = message.getPayload();
        Jedis jedis = new Jedis("localhost", 6379);  //指定Redis服务Host和port
        System.out.println("jedis :" + jedis);
        jedis.connect();
        //        jedis.close(); //使用完关闭连接
        try  {
            Integer integer = Integer.valueOf(payload);
            if (integer  == 477 ) {
                log.info(Thread.currentThread().getName() + " go to sleep.");
               try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tester?useSSL=false&serverTimezone=UTC", "root", "123456")){
               }catch (Exception e){
               }
                Thread.sleep(5000 * 1000);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId() + " -> message:" + payload);
    }


}
