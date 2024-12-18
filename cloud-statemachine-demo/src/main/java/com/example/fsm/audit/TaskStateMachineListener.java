package com.example.fsm.audit;

import com.example.fsm.event.TaskEventEnum;
import com.example.task.enums.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;

/**
 * @author Xiao Hong
 * @since 2024-12-18
 */
@Slf4j
@Service
public class TaskStateMachineListener extends StateMachineListenerAdapter<TaskStateEnum, TaskEventEnum> {

    @Override
    public void stateChanged(State<TaskStateEnum, TaskEventEnum> from, State<TaskStateEnum, TaskEventEnum> to) {
        log.info("TaskStateMachineListener stateChanged");
    }

    @Override
    public void stateEntered(State<TaskStateEnum, TaskEventEnum> state) {
        log.info("TaskStateMachineListener stateEntered");
    }

    @Override
    public void stateExited(State<TaskStateEnum, TaskEventEnum> state) {
        log.info("TaskStateMachineListener stateExited");
    }

    @Override
    public void eventNotAccepted(Message<TaskEventEnum> event) {
        log.info("TaskStateMachineListener eventNotAccepted");
    }

    @Override
    public void transition(Transition<TaskStateEnum, TaskEventEnum> transition) {
        log.info("TaskStateMachineListener transition");
    }

    @Override
    public void transitionStarted(Transition<TaskStateEnum, TaskEventEnum> transition) {
        log.info("TaskStateMachineListener transitionStarted");
    }

    @Override
    public void transitionEnded(Transition<TaskStateEnum, TaskEventEnum> transition) {
        log.info("TaskStateMachineListener transitionEnded");
    }

    @Override
    public void stateMachineStarted(StateMachine<TaskStateEnum, TaskEventEnum> stateMachine) {
        log.info("TaskStateMachineListener stateMachineStarted");
    }

    @Override
    public void stateMachineStopped(StateMachine<TaskStateEnum, TaskEventEnum> stateMachine) {
        log.info("TaskStateMachineListener stateMachineStopped");
    }

    @Override
    public void stateMachineError(StateMachine<TaskStateEnum, TaskEventEnum> stateMachine, Exception exception) {
        log.info("TaskStateMachineListener stateMachineError");
    }

    @Override
    public void extendedStateChanged(Object key, Object value) {
        log.info("TaskStateMachineListener extendedStateChanged");
    }

    @Override
    public void stateContext(StateContext<TaskStateEnum, TaskEventEnum> stateContext) {
        log.info("TaskStateMachineListener stateContext");
    }
}
