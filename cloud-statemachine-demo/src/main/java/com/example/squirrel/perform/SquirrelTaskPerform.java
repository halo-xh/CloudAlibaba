package com.example.squirrel.perform;

import com.example.squirrel.context.SquirrelTaskContext;
import com.example.squirrel.event.SquirrelTaskEvent;
import com.example.squirrel.machine.SquirrelTaskStateMachine;
import com.example.task.entity.Task;
import com.example.task.enums.TaskStateEnum;
import com.example.task.manager.TaskManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.squirrelframework.foundation.fsm.Action;

/**
 * @author fanyi.xh
 * @since 2024-12-19
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
public abstract class SquirrelTaskPerform implements Action<SquirrelTaskStateMachine, TaskStateEnum, SquirrelTaskEvent, SquirrelTaskContext> {

    private final TaskManager taskManager;

    protected SquirrelTaskPerform(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void execute(TaskStateEnum from, TaskStateEnum to, SquirrelTaskEvent event, SquirrelTaskContext context, SquirrelTaskStateMachine stateMachine) {
        // 通用方法
        beforeExecute(from, to, event, context);
        doExecute(from, to, event, context);
        afterExecute(from, to, event, context);
    }

    public abstract void doExecute(TaskStateEnum from, TaskStateEnum to, SquirrelTaskEvent event, SquirrelTaskContext context);


    private void beforeExecute(TaskStateEnum from, TaskStateEnum to, SquirrelTaskEvent event, SquirrelTaskContext context) {
        log.info("SquirrelTaskStateMachine beforeExecute fromState:{}, toState:{}, event:{}, context:{}", from, to, event, context);
    }

    private void afterExecute(TaskStateEnum from, TaskStateEnum to, SquirrelTaskEvent event, SquirrelTaskContext context) {
        log.info("SquirrelTaskStateMachine afterExecute fromState:{}, toState:{}, event:{}, context:{}", from, to, event, context);
        Task task = context.getTask();
        taskManager.updateStatus(task.getId(), to);
    }

    @Override
    public String name() {
        return this.getClass().getName();
    }

    @Override
    public int weight() {
        return 0;
    }

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public long timeout() {
        return Integer.MAX_VALUE;
    }

}
