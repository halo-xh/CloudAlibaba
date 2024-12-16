package com.example.fsm.action;

import com.example.fsm.event.TaskEventEnum;
import com.example.task.enums.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Service;

/**
 * @author Xiao Hong
 * @since 2024-12-14
 */
@Slf4j
@Service
public class HangupAction implements Action<TaskStateEnum, TaskEventEnum> {

    @Override
    public void execute(StateContext<TaskStateEnum, TaskEventEnum> context) {
        log.info("HangupAction... context:{}", context);

    }

}
