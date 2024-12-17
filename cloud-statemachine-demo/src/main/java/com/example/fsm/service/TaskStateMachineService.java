package com.example.fsm.service;

import com.example.fsm.event.TaskEventEnum;
import com.example.fsm.persist.TaskStateMachineStateMachinePersister;
import com.example.task.enums.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Service;

/**
 * @author Xiao Hong
 * @since 2024-12-17
 */
@Slf4j
@Service
public class TaskStateMachineService implements StateMachineService<TaskStateEnum, TaskEventEnum> {

    @Autowired
    private StateMachineFactory<TaskStateEnum, TaskEventEnum> stateMachineFactory;

    @Autowired
    private TaskStateMachineStateMachinePersister taskStateMachineStatePersister;


    @Override
    public StateMachine<TaskStateEnum, TaskEventEnum> acquireStateMachine(String machineId) {
        try {
            StateMachine<TaskStateEnum, TaskEventEnum> stateMachine = stateMachineFactory.getStateMachine(machineId);
            if (stateMachine != null) {
                StateMachineContext<TaskStateEnum, TaskEventEnum> context = taskStateMachineStatePersister.read(machineId);
                if (context != null) {
                    return restoreStateMachine(stateMachine, context);
                }
            }
            return stateMachine;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StateMachine<TaskStateEnum, TaskEventEnum> acquireStateMachine(String machineId, boolean start) {
        StateMachine<TaskStateEnum, TaskEventEnum> stateMachine = acquireStateMachine(machineId);
        if (stateMachine != null) {
            if (start) {
                stateMachine.start();
            }
        }
        return stateMachine;
    }

    @Override
    public void releaseStateMachine(String machineId) {


    }

    @Override
    public void releaseStateMachine(String machineId, boolean stop) {

    }


    protected StateMachine<TaskStateEnum, TaskEventEnum> restoreStateMachine(StateMachine<TaskStateEnum, TaskEventEnum> stateMachine, final StateMachineContext<TaskStateEnum, TaskEventEnum> stateMachineContext) {
        if (stateMachineContext == null) {
            return stateMachine;
        }
        stateMachine.stop();
        // only go via top region
        stateMachine.getStateMachineAccessor().doWithAllRegions(function -> function.resetStateMachine(stateMachineContext));
        return stateMachine;
    }


}
