package com.example.fsm.action;

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
public class HangupAction extends TaskAbstractAction {

    protected HangupAction(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void doExecute(StateContext<TaskStateEnum, TaskEventEnum> context) {

    }
}
