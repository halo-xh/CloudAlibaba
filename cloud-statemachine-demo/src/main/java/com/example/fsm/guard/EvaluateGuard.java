package com.example.fsm.guard;

import com.example.fsm.event.TaskEventEnum;
import com.example.task.enums.TaskStateEnum;
import com.example.task.manager.TaskManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Service;

/**
 * @author Xiao Hong
 * @since 2024-12-14
 */
@Slf4j
@Service
public class EvaluateGuard extends TaskAbstractGuard {


    protected EvaluateGuard(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public boolean doEvaluate(StateContext<TaskStateEnum, TaskEventEnum> context) {
        log.info("EvaluateGuard evaluate...");
        return true;
    }
}
