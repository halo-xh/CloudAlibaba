package com.example.fsm.persist;

import com.example.fsm.event.TaskEventEnum;
import com.example.task.enums.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.persist.AbstractPersistingStateMachineInterceptor;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.support.StateMachineInterceptor;
import org.springframework.stereotype.Service;

/**
 * @author Xiao Hong
 * @since 2024-12-16
 */
@Slf4j
@Service
public class TaskStateMachinePersisterInterceptor extends AbstractPersistingStateMachineInterceptor<TaskStateEnum, TaskEventEnum, String>
        implements StateMachineRuntimePersister<TaskStateEnum, TaskEventEnum, String> {


    @Autowired
    private TaskStateMachineStateMachinePersister taskStateMachineStateMachinePersister;


    @Override
    public void write(StateMachineContext<TaskStateEnum, TaskEventEnum> context, String contextObj) throws Exception {
        log.info(" context:{} contextObj:{}", context, contextObj);
        taskStateMachineStateMachinePersister.write(context, contextObj);
    }

    @Override
    public StateMachineContext<TaskStateEnum, TaskEventEnum> read(String contextObj) throws Exception {
        return taskStateMachineStateMachinePersister.read(contextObj);
    }

    @Override
    public StateMachineInterceptor<TaskStateEnum, TaskEventEnum> getInterceptor() {
        return this;
    }

}
