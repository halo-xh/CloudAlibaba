package com.example.fsm.guard;

import com.example.fsm.event.TaskEventEnum;
import com.example.task.enums.TaskStateEnum;
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
public class AcceptGuard implements Guard<TaskStateEnum, TaskEventEnum> {
    @Override
    public boolean evaluate(StateContext<TaskStateEnum, TaskEventEnum> context) {
        log.info("AcceptGuard evaluate...");
        return true;
    }
}
