package com.example.fsm.guard;

import com.example.fsm.event.TaskEventEnum;
import com.example.task.entity.Task;
import com.example.task.enums.TaskStateEnum;
import com.example.task.manager.TaskManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageHeaders;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.util.Assert;

/**
 * @author fanyi.xh
 * @since 2024-12-18
 */
@Slf4j
@Getter
public abstract class TaskAbstractGuard implements Guard<TaskStateEnum, TaskEventEnum> {


    private final TaskManager taskManager;

    protected TaskAbstractGuard(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public boolean evaluate(StateContext<TaskStateEnum, TaskEventEnum> context) {
        // 首先校验状态是否正确，禁止跳状态执行
        return sourceStateIsRight(context) && doEvaluate(context);
    }


    public abstract boolean doEvaluate(StateContext<TaskStateEnum, TaskEventEnum> context);

    protected boolean sourceStateIsRight(StateContext<TaskStateEnum, TaskEventEnum> context) {
        MessageHeaders headers = context.getMessageHeaders();
        Task task = headers.get("task", Task.class);
        Assert.notNull(task, "task is null");
        Long taskId = task.getId();
        TaskStateEnum sourceStateEnum = context.getSource().getId();
        log.info("TaskAbstractGuard taskId:{} sourceStateEnum:{} taskSate:{}", taskId, sourceStateEnum, task.getTaskStatus());
        return sourceStateEnum.equals(task.getTaskStatus());
    }


}
