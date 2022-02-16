package com.xh.cloudprovider8001;

import com.xh.cloudprovider8001.service.KafkaOutService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudProvider8001ApplicationTests {

    @Autowired
    private KafkaOutService kafkaOutService;

    @Test
    void contextLoads() {
        for (int i = 0; i < 100000000; i++) {
            kafkaOutService.sayHello(i+"");
        }
    }

}
