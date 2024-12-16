package com.example.fsm.transition;


import com.example.fsm.event.TaskEventEnum;
import com.example.task.entity.Task;
import com.example.task.enums.TaskStateEnum;
import com.example.task.mapper.TaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.annotation.*;
import org.springframework.statemachine.state.State;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Map;

@Slf4j
@WithStateMachine
public class TaskTransition {


    @Autowired
    private TaskMapper taskMapper;


    @OnTransition(source = "TO_DISPATCH")
    public void create(StateContext<TaskStateEnum, TaskEventEnum> context) {
        log.info("create a task context:{}",context);
        Task task = extractTaskFromContext(context);
        Long handlerId = task.getHandlerId();

    }



    @OnTransition(source = "TO_DISPATCH" , target = "TO_ACCEPT")
    public void toAccept(StateContext<TaskStateEnum, TaskEventEnum> context) {
        log.info("toAccept a task");
        //

    }


    @OnTransition(source = "TO_ACCEPT", target = "ACCEPTED")
    public void accepted(StateContext<TaskStateEnum, TaskEventEnum> context) {
        log.info("accepted a task");

    }

    @OnTransition(target = "HANDLING")
    public void handling(StateContext<TaskStateEnum, TaskEventEnum> context) {
        log.info("handling a task");
    }

    @OnTransition(target = "FINISHED")
    public void finished(StateContext<TaskStateEnum, TaskEventEnum> context) {
        log.info("finished a task");
    }

    @OnTransition(target = "AUDITING")
    public void auditing(StateContext<TaskStateEnum, TaskEventEnum> context) {
        log.info("auditing a task");
    }

    @OnTransition(target = "AUDITED")
    public void audited(StateContext<TaskStateEnum, TaskEventEnum> context) {
        log.info("audited a task");
    }

    @OnTransition(target = "CLOSED")
    public void closed(StateContext<TaskStateEnum, TaskEventEnum> context) {
        log.info("closed a task");
    }


//    @OnStateChanged
//    public void updateStatus(StateContext<TaskStateEnum, TaskEventEnum> context, @EventHeader(value = "task", required = true) Task task) {
//        log.info("context:{}", context);
//        State<TaskStateEnum, TaskEventEnum> target = context.getTarget();
//        TaskStateEnum stateEnum = target.getId();
//        log.info("updateStatus from:{} to:{}", task.getTaskStatus().getDescription(), stateEnum.getDescription());
//        task.setTaskStatus(stateEnum);
//        taskMapper.updateById(task);
//    }


    private Task extractTaskFromContext(StateContext<TaskStateEnum, TaskEventEnum> context) {
        MessageHeaders headers = context.getMessageHeaders();
        Task task = headers.get("task", Task.class);
        Assert.notNull(task, "task is null");
        return task;
    }

}
