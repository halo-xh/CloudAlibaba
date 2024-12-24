package com.example.task.service.impl;

import com.example.common.SnowflakeIdWorker;
import com.example.fsm.event.TaskEventEnum;
import com.example.squirrel.context.SquirrelTaskContext;
import com.example.squirrel.engine.SquirrelTaskStateMachineDefinition;
import com.example.squirrel.engine.SquirrelTaskStateMachineEngine;
import com.example.squirrel.event.SquirrelTaskEvent;
import com.example.task.controller.request.TaskCreateRequest;
import com.example.task.convertor.TaskConvertor;
import com.example.task.entity.Task;
import com.example.task.entity.TaskStateMachineDefinition;
import com.example.task.entity.TaskType;
import com.example.task.enums.TaskStateEnum;
import com.example.task.manager.TaskManager;
import com.example.task.manager.TaskStateMachineDefinitionManager;
import com.example.task.manager.TaskTypeManager;
import com.example.task.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * <p>
 * 状态机demo任务表 服务实现类
 * </p>
 *
 * @author xh
 * @since 2024-08-18
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private TaskTypeManager taskTypeManager;

    @Autowired
    private StateMachineService<TaskStateEnum, TaskEventEnum> stateMachineService;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private TaskStateMachineDefinitionManager taskStateMachineDefinitionManager;

    @Autowired
    private SquirrelTaskStateMachineEngine squirrelTaskStateMachineEngine;

    @Autowired
    private ObjectMapper objectMapper;

    private static final TaskConvertor TASK_CONVERTOR = Mappers.getMapper(TaskConvertor.class);


    @Override
    public Long submitTask(TaskCreateRequest request) {
        log.info("createTask request:{}", request);
        Task task = TASK_CONVERTOR.toTask(request);
        TaskType taskType = taskTypeManager.findById(task.getTaskType());
        Assert.notNull(taskType, "任务类型不存在");
        task.setTaskStatus(TaskStateEnum.CREATED);
        task.setTaskStates(taskType.getTaskStates());
        long id = snowflakeIdWorker.nextId();
        task.setId(id);
        taskManager.create(task);
        createAndFireEventWithStateMachine(id, TaskEventEnum.SUBMIT);
        createAndFireEventWithSquirrelStateMachine(id, TaskEventEnum.SUBMIT);
        return id;
    }

    @Override
    public void acceptTask(Long id) {
        log.info("acceptTask id:{}", id);
        createAndFireEventWithStateMachine(id, TaskEventEnum.ACCEPT);
    }

    @Override
    public void rejectTask(Long id) {
        log.info("rejectTask id:{}", id);
        createAndFireEventWithStateMachine(id, TaskEventEnum.REJECT);
    }

    @Override
    public void finishTask(Long id) {
        log.info("finishTask id:{}", id);
        createAndFireEventWithStateMachine(id, TaskEventEnum.FINISH);
    }

    @Override
    public void evaluateTask(Long id) {
        log.info("evaluateTask id:{}", id);
        createAndFireEventWithStateMachine(id, TaskEventEnum.EVALUATE);
    }

    @Override
    public void passEvaluateTask(Long id) {
        log.info("passEvaluateTask id:{}", id);
        createAndFireEventWithStateMachine(id, TaskEventEnum.PASS_EVALUATE);
    }

    @Override
    public void rejectEvaluateTask(Long id) {
        log.info("rejectEvaluateTask id:{}", id);
        createAndFireEventWithStateMachine(id, TaskEventEnum.REJECT_EVALUATE);
    }

    @Override
    public void closeTask(Long id) {
        log.info("closeTask id:{}", id);
        createAndFireEventWithStateMachine(id, TaskEventEnum.CLOSE);
    }

    @Override
    public void hangUpTask(Long id) {
        log.info("hangUpTask id:{}", id);
        createAndFireEventWithStateMachine(id, TaskEventEnum.HANG_UP);
    }

    @Override
    public void hangDownTask(Long id) {
        log.info("hangDownTask id:{}", id);
        createAndFireEventWithStateMachine(id, TaskEventEnum.HANG_DOWN);
    }

    @Override
    public void transTask(Long id) {
        log.info("transTask id:{}", id);
        createAndFireEventWithStateMachine(id, TaskEventEnum.TRANS);
    }

    @Override
    public void handleTask(Long id) {
        log.info("handleTask id:{}", id);
        createAndFireEventWithStateMachine(id, TaskEventEnum.HANDLE);
    }

    @Override
    public void dispatchTask(Long id) {
        log.info("dispatchTask id:{}", id);
        createAndFireEventWithStateMachine(id, TaskEventEnum.DISPATCHED);
    }


    private void createAndFireEventWithStateMachine(Long taskId, TaskEventEnum event) {
        RLock lock = redissonClient.getLock(String.valueOf(taskId));
        if (!lock.tryLock()) {
            throw new RuntimeException("正在处理:" + event.getDescription());
        }
        try {
            log.info("createAndStartStateMachine taskId:{} event:{}", taskId, event.getDescription());
            Task task = taskManager.findById(taskId);
            Assert.notNull(task, "task is null");
            Message<TaskEventEnum> message = MessageBuilder.withPayload(event).setHeader("task", task).build();
            StateMachine<TaskStateEnum, TaskEventEnum> stateMachine = stateMachineService.acquireStateMachine(String.valueOf(taskId));
            stateMachine.start();
            stateMachine.sendEvent(message);
            boolean hasStateMachineError = stateMachine.hasStateMachineError();
            Assert.isTrue(!hasStateMachineError, "创建状态机系统错误");
        } finally {
            lock.unlock();
        }
    }

    @SneakyThrows
    private void createAndFireEventWithSquirrelStateMachine(Long taskId, TaskEventEnum event) {
        RLock lock = redissonClient.getLock(String.valueOf(taskId));
        if (!lock.tryLock()) {
            throw new RuntimeException("正在处理:" + event.getDescription());
        }
        try {
            SquirrelTaskContext squirrelTaskContext = new SquirrelTaskContext();
            Task task = taskManager.findById(taskId);
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
        } finally {
            lock.unlock();
        }
    }


}
