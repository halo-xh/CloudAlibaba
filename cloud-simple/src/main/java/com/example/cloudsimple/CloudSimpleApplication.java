package com.example.cloudsimple;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RTransaction;
import org.redisson.api.RedissonClient;
import org.redisson.api.TransactionOptions;
import org.redisson.client.codec.ByteArrayCodec;
import org.redisson.client.codec.Codec;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.SnappyCodecV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.support.GenericMessage;

import java.util.concurrent.TimeUnit;


@Slf4j
@SpringBootApplication
public class CloudSimpleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CloudSimpleApplication.class, args);
//        RedissonClient rc = context.getBean(RedissonClient.class);
//        Runnable runnable = () ->{
//            long l = System.currentTimeMillis();
//            RTransaction transaction = rc.createTransaction(TransactionOptions.defaults());
//            RMap<String, Long> map = transaction.getMap("cc", StringCodec.INSTANCE);
//            map.addAndGet("k1",1);
//            map.addAndGet("k2",1);
//            map.addAndGet("k3",1);
//            transaction.commit();
//            long l12 = System.currentTimeMillis();
//            System.out.println("l12 - l = " + (l12 - l) + "----" );
//        };
//        for (int i = 0; i < 30; i++) {
//            System.out.println("======================================== = " + i);
//            new Thread(runnable).start();
//        }
//        RMap<String, Long> cc = rc.getMap("cc");
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        for (String s : cc.keySet()) {
//            System.out.println("cc.get(s) = " + cc.get(s));
//        }

        for (int h = 0; h < 20; h++) {
            StringRedisTemplate template = context.getBean(StringRedisTemplate.class);
            Runnable runnable = () -> {
                long l = System.currentTimeMillis();
                Object execute = template.execute(new SessionCallback<Object>() {
                    @Override
                    public Object execute(RedisOperations operations) throws DataAccessException {
                        operations.multi();
                        operations.opsForValue().increment("MKT_ACT_ACC" + ":" + 123 + ":" + 666 + ":" + "kuku");
                        operations.opsForValue().increment("MKT_ACT_ACC" + ":" + 123 + ":" + 666);
                        return operations.exec();
                    }
                });
                long l12 = System.currentTimeMillis();
                log.error("l12 - l = " + (l12 - l) + "----" + execute);
            };
            for (int i = 0; i < 30; i++) {
                System.out.println("======================================== = " + i);
                new Thread(runnable).start();
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(template.opsForValue().get("MKT_ACT_ACC" + ":" + 123 + ":" + 666 + ":" + "kuku"));
            System.out.println(template.opsForValue().get("MKT_ACT_ACC" + ":" + 123 + ":" + 666));
        }

    }

//    {
//        StringRedisTemplate template = context.getBean(StringRedisTemplate.class);
//        Runnable runnable = () -> {
//            long l = System.currentTimeMillis();
//            Object execute = template.execute(new SessionCallback<Object>() {
//                @Override
//                public Object execute(RedisOperations operations) throws DataAccessException {
//                    operations.multi();
//                    operations.opsForValue().increment("MKT_ACT_ACC" + ":" + 123 + ":" + 666 + ":" + "kuku");
//                    operations.opsForValue().increment("MKT_ACT_ACC" + ":" + 123 + ":" + 666);
//                    return operations.exec();
//                }
//            });
//            long l12 = System.currentTimeMillis();
//            System.out.println("l12 - l = " + (l12 - l) + "----" + execute);
//        };
//        for (int i = 0; i < 30; i++) {
//            System.out.println("======================================== = " + i);
//            new Thread(runnable).start();
//        }
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(template.opsForValue().get("MKT_ACT_ACC" + ":" + 123 + ":" + 666 + ":" + "kuku"));
//        System.out.println(template.opsForValue().get("MKT_ACT_ACC" + ":" + 123 + ":" + 666));
//    }

}
