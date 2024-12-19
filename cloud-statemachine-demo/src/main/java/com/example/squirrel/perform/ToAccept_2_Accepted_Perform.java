package com.example.squirrel.perform;

import com.example.squirrel.context.SquirrelTaskContext;
import com.example.squirrel.event.SquirrelTaskEvent;
import com.example.task.enums.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author fanyi.xh
 * @since 2024-12-19
 */
@Slf4j
@Service
public class ToAccept_2_Accepted_Perform extends SquirrelTaskPerform {
    @Override
    public void doExecute(TaskStateEnum from, TaskStateEnum to, SquirrelTaskEvent event, SquirrelTaskContext context) {

    }
}
