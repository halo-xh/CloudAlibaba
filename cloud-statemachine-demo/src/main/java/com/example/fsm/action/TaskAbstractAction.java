package com.example.fsm.action;

import com.example.fsm.event.TaskEventEnum;
import com.example.task.entity.Task;
import com.example.task.enums.TaskStateEnum;
import com.example.task.manager.TaskManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageHeaders;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.util.Assert;

/**
 * @author fanyi.xh
 * @since 2024-12-18
 */
@Slf4j
@Getter
public abstract class TaskAbstractAction implements Action<TaskStateEnum, TaskEventEnum> {


    private final TaskManager taskManager;

    protected TaskAbstractAction(TaskManager taskManager) {
        this.taskManager = taskManager;
    }


    @Override
    public void execute(StateContext<TaskStateEnum, TaskEventEnum> context) {
        doExecute(context);
        // after business logic, update status.
        updateStatus(context);
    }

    public abstract void doExecute(StateContext<TaskStateEnum, TaskEventEnum> context);

    protected void updateStatus(StateContext<TaskStateEnum, TaskEventEnum> context) {
        MessageHeaders headers = context.getMessageHeaders();
        StateContext.Stage stage = context.getStage();
        Task task = headers.get("task", Task.class);
        Assert.notNull(task, "task is null");
        Long taskId = task.getId();
        TaskStateEnum sourceStateEnum = context.getSource().getId();
        TaskStateEnum targetStateEnum = context.getTarget().getId();
        log.info("TaskAbstractAction taskId:{} sourceStateEnum:{} targetStateEnum:{}", taskId, sourceStateEnum, targetStateEnum);
        boolean updated = taskManager.updateStatus(taskId, targetStateEnum);
        Assert.isTrue(updated, "updateStatus failed");
    }


}
