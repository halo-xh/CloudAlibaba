package com.example.cloudstatemachinedemo;

import com.example.fsm.config.StateMachineConfig;
import com.example.fsm.event.TaskEventEnum;
import com.example.task.enums.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;


@Slf4j
@SpringBootTest
class StateMachineTests {

    @Autowired
    private StateMachineConfig builder;

    @Autowired
    private BeanFactory beanFactory;


    @Test
    void test() throws Exception {

    }
}
