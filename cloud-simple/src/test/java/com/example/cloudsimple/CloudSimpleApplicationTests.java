package com.example.cloudsimple;

import com.example.cloudsimple.test.TestEventListener;
import com.example.cloudsimple.test.TestEventListener2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudSimpleApplicationTests {

    @Autowired
    private TestEventListener eventListener;

    @Autowired
    private TestEventListener2 eventListener2;


    @Test
    void contextLoads() {
        eventListener.createTestDO();
    }

    @Test
    void contextLoads2() {
        eventListener2.createTestDO();
    }

}
