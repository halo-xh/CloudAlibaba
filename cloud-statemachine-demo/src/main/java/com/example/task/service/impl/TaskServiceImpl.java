package com.example.task.service.impl;

import com.example.fsm.event.TaskEventEnum;
import com.example.task.controller.request.TaskCreateRequest;
import com.example.task.convertor.TaskConvertor;
import com.example.task.entity.Task;
import com.example.task.entity.TaskType;
import com.example.task.enums.TaskStateEnum;
import com.example.task.mapper.TaskMapper;
import com.example.task.mapper.TaskTypeMapper;
import com.example.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
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
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskTypeMapper taskTypeMapper;

    @Autowired
    private StateMachineService<TaskStateEnum, TaskEventEnum> stateMachineService;


    private static final TaskConvertor TASK_CONVERTOR = Mappers.getMapper(TaskConvertor.class);


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createTask(TaskCreateRequest request) throws Exception {
        Task task = TASK_CONVERTOR.toTask(request);
        TaskType taskType = taskTypeMapper.findById(task.getTaskType());
        Assert.notNull(taskType, "任务类型不存在");
        task.setTaskStatus(TaskStateEnum.TO_DISPATCH);
        task.setTaskStates(taskType.getTaskStates());
        taskMapper.insert(task);
        createAndFireEventWithStateMachine(task.getId(), TaskEventEnum.SUBMIT);
        return task.getId();
    }

    @Override
    public void acceptTask(Long id) throws Exception {
        createAndFireEventWithStateMachine(id, TaskEventEnum.ACCEPT);
    }

    private void createAndFireEventWithStateMachine(Long taskId, TaskEventEnum event) throws Exception {
        log.info("createAndStartStateMachine taskId:{} event:{}", taskId, event.getDescription());
        Task task = taskMapper.findById(taskId);
        Message<TaskEventEnum> message = MessageBuilder.withPayload(event).setHeader("task", task).build();
        StateMachine<TaskStateEnum, TaskEventEnum> stateMachine = stateMachineService.acquireStateMachine(String.valueOf(taskId));
        stateMachine.start();
        stateMachine.sendEvent(message);
        boolean hasStateMachineError = stateMachine.hasStateMachineError();
        Assert.isTrue(!hasStateMachineError, "创建状态机系统错误");
    }


}
