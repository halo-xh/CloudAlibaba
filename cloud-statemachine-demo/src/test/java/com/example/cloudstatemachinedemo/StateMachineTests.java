package com.example.cloudstatemachinedemo;

import com.example.fsm.event.TaskEventEnum;
import com.example.squirrel.context.SquirrelTaskContext;
import com.example.squirrel.engine.SquirrelTaskStateMachineDefinition;
import com.example.squirrel.engine.SquirrelTaskStateMachineEngine;
import com.example.squirrel.event.SquirrelTaskEvent;
import com.example.task.entity.Task;
import com.example.task.entity.TaskStateMachineDefinition;
import com.example.task.entity.TaskType;
import com.example.task.enums.TaskStateEnum;
import com.example.task.manager.TaskManager;
import com.example.task.manager.TaskStateMachineDefinitionManager;
import com.example.task.manager.TaskTypeManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
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
    private StateMachineService<TaskStateEnum, TaskEventEnum> stateMachineService;



    @Autowired
    private SquirrelTaskStateMachineEngine squirrelTaskStateMachineEngine;

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private TaskTypeManager taskTypeManager;

    @Autowired
    private TaskStateMachineDefinitionManager taskStateMachineDefinitionManager;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void test() throws Exception {
        Long id = 1064367613742366720L;
        createAndFireEventWithStateMachine(id, TaskEventEnum.SUBMIT);
        createAndFireEventWithStateMachine(id, TaskEventEnum.DISPATCHED);
        createAndFireEventWithStateMachine(id, TaskEventEnum.ACCEPT);
        createAndFireEventWithStateMachine(id, TaskEventEnum.HANDLE);
        createAndFireEventWithStateMachine(id, TaskEventEnum.HANG_UP);
        createAndFireEventWithStateMachine(id, TaskEventEnum.HANG_DOWN);
        createAndFireEventWithStateMachine(id, TaskEventEnum.FINISH);
        createAndFireEventWithStateMachine(id, TaskEventEnum.EVALUATE);
        createAndFireEventWithStateMachine(id, TaskEventEnum.REJECT_EVALUATE);
        createAndFireEventWithStateMachine(id, TaskEventEnum.HANG_UP);
        createAndFireEventWithStateMachine(id, TaskEventEnum.HANG_DOWN);
        createAndFireEventWithStateMachine(id, TaskEventEnum.FINISH);
        createAndFireEventWithStateMachine(id, TaskEventEnum.EVALUATE);
        createAndFireEventWithStateMachine(id, TaskEventEnum.PASS_EVALUATE);
    }

    private void createAndFireEventWithStateMachine(Long taskId, TaskEventEnum event) throws Exception {
        log.info("createAndStartStateMachine taskId:{} event:{}", taskId, event.getDescription());
        Task task = taskManager.findById(taskId);
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

    @SneakyThrows
    @Test
    void testSq() {
        SquirrelTaskContext squirrelTaskContext = new SquirrelTaskContext();
        Task task = taskManager.findById(1064332199027490816L);
        squirrelTaskContext.setTask(task);
        TaskType taskType = taskTypeManager.findById(task.getTaskType());
        squirrelTaskContext.setTaskType(taskType);
        TaskStateMachineDefinition machineDefinition = taskStateMachineDefinitionManager.findByTaskId(task.getId());
        if (machineDefinition == null) {
            TaskStateMachineDefinition definition = new TaskStateMachineDefinition();
            definition.setTaskId(task.getId());
            SquirrelTaskStateMachineDefinition squirrelTaskStateMachineDefinition = new SquirrelTaskStateMachineDefinition();
            squirrelTaskStateMachineDefinition.setTaskStates(taskType.getTaskStates());
            squirrelTaskStateMachineDefinition.setNeedAudit(taskType.getNeedAudit());
            squirrelTaskStateMachineDefinition.setNeedManualDispatch(taskType.getNeedManualDispatch());
            squirrelTaskStateMachineDefinition.setNeedInviteEvaluation(taskType.getNeedInviteEvaluation());
            squirrelTaskStateMachineDefinition.setAllowHuangUp(taskType.getAllowHuangUp());
            definition.setDefinition(objectMapper.writeValueAsString(squirrelTaskStateMachineDefinition));
            taskStateMachineDefinitionManager.save(definition);
            squirrelTaskContext.setMachineDefinition(definition);
        } else {
            squirrelTaskContext.setMachineDefinition(machineDefinition);
        }
        squirrelTaskStateMachineEngine.createAndFire(squirrelTaskContext, SquirrelTaskEvent.SUBMIT);
    }

}
