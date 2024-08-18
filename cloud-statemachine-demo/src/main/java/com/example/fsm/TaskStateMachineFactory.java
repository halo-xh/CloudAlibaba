package com.example.fsm;


import com.example.fsm.event.TaskEventEnum;
import com.example.task.entity.Task;
import com.example.task.enums.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.HashSet;

@Slf4j
@Service
public class TaskStateMachineFactory {

    @Autowired
    private BeanFactory beanFactory;

    public StateMachine<TaskStateEnum, TaskEventEnum> createDefault(String machineId) throws Exception {
        StateMachineBuilder.Builder<TaskStateEnum, TaskEventEnum> builder = StateMachineBuilder.builder();

        builder
                .configureConfiguration()
                .withConfiguration()
                .machineId(machineId)
                .beanFactory(beanFactory);

        builder
                .configureStates()
                .withStates()
                .initial(TaskStateEnum.TO_DISPATCH)
                .states(new HashSet<>(EnumSet.allOf(TaskStateEnum.class)));

        builder
                .configureTransitions()
                .withExternal()
                .source(TaskStateEnum.TO_DISPATCH).target(TaskStateEnum.TO_ACCEPT)
                .event(TaskEventEnum.CREATED)
                .guard(specifiedHandler())
                .and()
                .withExternal()
                .source(TaskStateEnum.TO_DISPATCH).target(TaskStateEnum.TO_ACCEPT)
                .event(TaskEventEnum.DISPATCHED)
                .and()
                .withExternal()
                .source(TaskStateEnum.TO_ACCEPT).target(TaskStateEnum.ACCEPTED)
                .event(TaskEventEnum.ACCEPT)
                .and()
                .withExternal()
                .source(TaskStateEnum.ACCEPTED).target(TaskStateEnum.HANDLING)
                .event(TaskEventEnum.HANDLE)
                .and()
                .withExternal()
                .source(TaskStateEnum.HANDLING).target(TaskStateEnum.FINISHED)
                .event(TaskEventEnum.FINISH)
                .and()
                .withExternal()
                .source(TaskStateEnum.FINISHED).target(TaskStateEnum.AUDITING)
                .event(TaskEventEnum.EVALUATE)
                .and()
                .withExternal()
                .source(TaskStateEnum.AUDITING).target(TaskStateEnum.AUDITED)
                .event(TaskEventEnum.AUDIT)
                .and()
                .withExternal()
                .source(TaskStateEnum.AUDITED).target(TaskStateEnum.CLOSED)
                .event(TaskEventEnum.CLOSE)
                .and()
                .withExternal()
                .source(TaskStateEnum.AUDITING).target(TaskStateEnum.TO_ACCEPT)
                .event(TaskEventEnum.REJECT);

        return builder.build();
    }


    /**
     * 存在处理人
     * @return Guard
     */
    private Guard<TaskStateEnum,TaskEventEnum> specifiedHandler() {
        return context -> {
            MessageHeaders messageHeaders = context.getMessageHeaders();
            Task task = messageHeaders.get("task", Task.class);
            if (task == null) {
                return false;
            }
            Long handlerId = task.getHandlerId();
            return handlerId != null;
        };
    }

}
