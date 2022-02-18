package com.xh.cloudprovider8001;

import com.xh.cloudprovider8001.redission.designresource.RedisDesignResourceSnGenerator;
import com.xh.cloudprovider8001.service.KafkaOutService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CloudProvider8001Application.class})
class CloudProvider8001ApplicationTests {

    @Autowired
    private KafkaOutService kafkaOutService;

    @Test
    void contextLoads() {
        for (int i = 0; i < 100000000; i++) {
            kafkaOutService.sayHello(i+"");
        }
    }

    @Autowired
    private RedisDesignResourceSnGenerator redisDesignResourceSnGenerator;

    @Test
    public void create() {
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < 30; j++) {
                    String sequence = redisDesignResourceSnGenerator.getSn("ADT" + finalI, false);
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
