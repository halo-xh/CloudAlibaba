package com.example.cloudsimple;

import com.example.cloudsimple.test.TestEventListener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudSimpleApplicationTests {

    @Autowired
    private TestEventListener eventListener;

    @Test
    void contextLoads() {
        eventListener.createTestDO();
    }

}
