package com.xh.cloudprovider8001;

import com.xh.cloudprovider8002.CloudProvider8002Application;
import com.xh.cloudprovider8002.sn.core.service.SequenceDetailService;
import com.xh.cloudprovider8002.sn.designresource.MysqlDesignResourceSnGenerator;
import com.xh.cloudprovider8002.sn.designresource.RedisDesignResourceSnGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CloudProvider8002Application.class})
public class CloudProvider8002ApplicationTests {


    @Autowired
    private SequenceDetailService cmSnGenerateService;

    @Autowired
    private MysqlDesignResourceSnGenerator sequenceGenerator;

    @Autowired
    private RedisDesignResourceSnGenerator redisDesignResourceSnGenerator;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedissonClient redissonClient;


    @Test
    public void redissonClient() {
        RAtomicLong asd = redissonClient.getAtomicLong("asd");
        long l = asd.addAndGet(1L);
        System.out.println("l = " + l);
        RAtomicLong asd2 = redissonClient.getAtomicLong("asd");
        long l2 = asd2.addAndGet(1L);
        System.out.println("l2 = " + l2);
    }

    @Test
    public void create() {
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < 30; j++) {
                    String sequence = sequenceGenerator.getSn("ADW" + finalI, false);
                    System.out.println("Thread = " + Thread.currentThread().getId() + " --> seq = " + sequence);
                }
            }).start();
        }
        try {
            Thread.sleep(10000 * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
