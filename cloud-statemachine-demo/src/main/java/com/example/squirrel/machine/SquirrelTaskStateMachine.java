package com.example.squirrel.machine;

import com.example.squirrel.context.SquirrelTaskContext;
import com.example.squirrel.event.SquirrelTaskEvent;
import com.example.task.enums.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.squirrelframework.foundation.fsm.impl.AbstractStateMachine;

/**
 * @author fanyi.xh
 * @since 2024-12-19
 */
@Slf4j
public class SquirrelTaskStateMachine extends AbstractStateMachine<SquirrelTaskStateMachine, TaskStateEnum, SquirrelTaskEvent, SquirrelTaskContext> {


    @Override
    protected void afterActionInvoked(TaskStateEnum fromState, TaskStateEnum toState, SquirrelTaskEvent event, SquirrelTaskContext context) {
        log.info("SquirrelTaskStateMachine afterActionInvoked fromState:{}, toState:{}, event:{}, context:{}", fromState, toState, event, context);
    }

    @Override
    protected void beforeActionInvoked(TaskStateEnum fromState, TaskStateEnum toState, SquirrelTaskEvent event, SquirrelTaskContext context) {
        log.info("SquirrelTaskStateMachine beforeActionInvoked fromState:{}, toState:{}, event:{}, context:{}", fromState, toState, event, context);
    }

    @Override
    protected void afterTransitionDeclined(TaskStateEnum fromState, SquirrelTaskEvent event, SquirrelTaskContext context) {
        log.info("SquirrelTaskStateMachine afterTransitionDeclined fromState:{}, event:{}, context:{}", fromState, event, context);
    }

    @Override
    protected void afterTransitionEnd(TaskStateEnum fromState, TaskStateEnum toState, SquirrelTaskEvent event, SquirrelTaskContext context) {
        log.info("SquirrelTaskStateMachine afterTransitionEnd fromState:{}, toState:{}, event:{}, context:{}", fromState, toState, event, context);
    }

    @Override
    protected void afterTransitionCompleted(TaskStateEnum fromState, TaskStateEnum toState, SquirrelTaskEvent event, SquirrelTaskContext context) {
        log.info("SquirrelTaskStateMachine afterTransitionCompleted fromState:{}, toState:{}, event:{}, context:{}", fromState, toState, event, context);
    }

    @Override
    protected void beforeTransitionBegin(TaskStateEnum fromState, SquirrelTaskEvent event, SquirrelTaskContext context) {
        log.info("SquirrelTaskStateMachine beforeTransitionBegin fromState:{}, event:{}, context:{}", fromState, event, context);
    }


}
