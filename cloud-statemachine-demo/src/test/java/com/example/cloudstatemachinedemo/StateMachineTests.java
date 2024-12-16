package com.example.cloudstatemachinedemo;

import com.example.fsm.config.StateMachineConfig;
import com.example.fsm.event.TaskEventEnum;
import com.example.task.entity.Task;
import com.example.task.enums.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.statemachine.state.State;
import org.springframework.util.Assert;


@Slf4j
@SpringBootTest
class StateMachineTests {

    @Autowired
    private StateMachineConfig builder;

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private StateMachineService<TaskStateEnum, TaskEventEnum> stateMachineService;



    @Test
    void test() throws Exception {
        createAndFireEventWithStateMachine(1111L,TaskEventEnum.SUBMIT);
        createAndFireEventWithStateMachine(1111L,TaskEventEnum.ACCEPT);
    }

    private void createAndFireEventWithStateMachine(Long taskId, TaskEventEnum event) throws Exception {
        log.info("createAndStartStateMachine taskId:{} event:{}", taskId, event.getDescription());
        Task task = new Task();
        Message<TaskEventEnum> message = MessageBuilder.withPayload(event).setHeader("task", task).build();
        StateMachine<TaskStateEnum, TaskEventEnum> stateMachine = stateMachineService.acquireStateMachine(String.valueOf(taskId));
        stateMachine.start();
        stateMachine.sendEvent(message);
        State<TaskStateEnum, TaskEventEnum> state =
                stateMachine.getState();
        System.out.println(state);
        boolean hasStateMachineError = stateMachine.hasStateMachineError();
        Assert.isTrue(!hasStateMachineError, "创建状态机系统错误");
        stateMachine.stop();
    }

}
