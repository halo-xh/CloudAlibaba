package com.example.cloudsimple.test;


import com.example.cloudsimple.entity.TestDO;
import com.example.cloudsimple.request.TestDOEvent;
import com.example.cloudsimple.service.TestDOService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class TestEventListener {

    @Resource
    private TestDOService testDOService;

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Transactional(rollbackFor = Exception.class)
    public void createTestDO() {
        TestDO testDO = new TestDO();
        testDO.setId(7L);
        testDO.setVersion(1);
        applicationEventPublisher.publishEvent(new TestDOEvent(1L,"q"));
        System.out.println("after publiser ---------------> ");
        testDO.setValue(1);
        testDOService.create(testDO);
    }

}
