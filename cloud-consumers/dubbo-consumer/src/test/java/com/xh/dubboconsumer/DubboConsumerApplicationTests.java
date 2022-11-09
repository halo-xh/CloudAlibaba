package com.xh.dubboconsumer;

import com.xh.clouddubbo.dto.DubboTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DubboConsumerApplicationTests {

    @Autowired
    private DubboConsumerTester dubboConsumerTester;

    @Test
    void contextLoads() {
        DubboTest byId = dubboConsumerTester.getById(1L);
        System.out.println("byId = " + byId);
    }

}
