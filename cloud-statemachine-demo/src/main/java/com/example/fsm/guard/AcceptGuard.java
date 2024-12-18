package com.example.fsm.guard;

import com.example.fsm.event.TaskEventEnum;
import com.example.task.enums.TaskStateEnum;
import com.example.task.manager.TaskManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Service;

/**
 * @author Xiao Hong
 * @since 2024-12-14
 */
@Slf4j
@Service
public class AcceptGuard extends TaskAbstractGuard {

    protected AcceptGuard(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public boolean doEvaluate(StateContext<TaskStateEnum, TaskEventEnum> context) {
        log.info("AcceptGuard evaluate...");
        return true;
    }

}
