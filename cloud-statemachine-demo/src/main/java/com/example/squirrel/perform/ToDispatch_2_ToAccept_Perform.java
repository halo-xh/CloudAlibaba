package com.example.squirrel.perform;

import com.example.squirrel.context.SquirrelTaskContext;
import com.example.squirrel.event.SquirrelTaskEvent;
import com.example.task.enums.TaskStateEnum;
import com.example.task.manager.TaskManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author fanyi.xh
 * @since 2024-12-19
 */
@Slf4j
@Service
public class ToDispatch_2_ToAccept_Perform extends SquirrelTaskPerform {


    public ToDispatch_2_ToAccept_Perform(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void doExecute(TaskStateEnum from, TaskStateEnum to, SquirrelTaskEvent event, SquirrelTaskContext context) {
        log.info("ToDispatch_2_ToAccept_Perform doExecute fromState:{}, toState:{}, event:{}, context:{}", from, to, event, context);
    }

}