package com.example.fsm.config;


import com.example.fsm.action.AcceptAction;
import com.example.fsm.action.DispatchAction;
import com.example.fsm.action.EvaluateAction;
import com.example.fsm.action.FinishAction;
import com.example.fsm.action.HandleAction;
import com.example.fsm.action.HangDownAction;
import com.example.fsm.action.HangupAction;
import com.example.fsm.action.PassEvaluateAction;
import com.example.fsm.action.SubmitAction;
import com.example.fsm.event.TaskEventEnum;
import com.example.fsm.guard.AcceptGuard;
import com.example.fsm.guard.DispatchGuard;
import com.example.fsm.guard.EvaluateGuard;
import com.example.fsm.guard.FinishGuard;
import com.example.fsm.guard.HandleGuard;
import com.example.fsm.guard.HangDownGuard;
import com.example.fsm.guard.HangupGuard;
import com.example.fsm.guard.PassEvaluateGuard;
import com.example.fsm.guard.SubmitGuard;
import com.example.task.enums.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.StateDoActionPolicy;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;

import java.util.EnumSet;
import java.util.HashSet;


@Slf4j
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<TaskStateEnum, TaskEventEnum> {

    @Autowired
    private ApplicationContext applicationContext;


    @Autowired
    private StateMachineRuntimePersister<TaskStateEnum, TaskEventEnum, String> taskStateMachinePersister;

//    @Bean
//    public StateMachineService<TaskStateEnum, TaskEventEnum> stateMachineService(StateMachineFactory<TaskStateEnum, TaskEventEnum> stateMachineFactory) {
//        return new DefaultStateMachineService<>(stateMachineFactory, taskStateMachinePersister);
//    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<TaskStateEnum, TaskEventEnum> config) throws Exception {
         config.withPersistence()
                 .runtimePersister(taskStateMachinePersister)
                 .and()
                 .withConfiguration()
                 .autoStartup(false)
                 .stateDoActionPolicy(StateDoActionPolicy.IMMEDIATE_CANCEL);

    }

    @Override
    public void configure(StateMachineTransitionConfigurer<TaskStateEnum, TaskEventEnum> transitions) throws Exception {
        transitions
                //
                .withExternal()
                .source(TaskStateEnum.CREATED).target(TaskStateEnum.TO_DISPATCH)
                .event(TaskEventEnum.SUBMIT)
                .guard(applicationContext.getBean(SubmitGuard.class))
                .action(applicationContext.getBean(SubmitAction.class))
                //
                .and()
                .withExternal()
                .source(TaskStateEnum.TO_DISPATCH).target(TaskStateEnum.TO_ACCEPT)
                .event(TaskEventEnum.DISPATCHED)
                .guard(applicationContext.getBean(DispatchGuard.class))
                .action(applicationContext.getBean(DispatchAction.class))
                //
                .and()
                .withExternal()
                .source(TaskStateEnum.TO_ACCEPT).target(TaskStateEnum.ACCEPTED)
                .event(TaskEventEnum.ACCEPT)
                .guard(applicationContext.getBean(AcceptGuard.class))
                .action(applicationContext.getBean(AcceptAction.class))
                //
                .and()
                .withExternal()
                .source(TaskStateEnum.ACCEPTED).target(TaskStateEnum.HANDLING)
                .event(TaskEventEnum.HANDLE)
                .guard(applicationContext.getBean(HandleGuard.class))
                .action(applicationContext.getBean(HandleAction.class))
                //
                .and()
                .withExternal()
                .source(TaskStateEnum.HANDLING).target(TaskStateEnum.SUSPEND)
                .event(TaskEventEnum.HANG_UP)
                .guard(applicationContext.getBean(HangupGuard.class))
                .action(applicationContext.getBean(HangupAction.class))
                //
                .and()
                .withExternal()
                .source(TaskStateEnum.SUSPEND).target(TaskStateEnum.HANDLING)
                .event(TaskEventEnum.HANG_DOWN)
                .guard(applicationContext.getBean(HangDownGuard.class))
                .action(applicationContext.getBean(HangDownAction.class))
                //
                .and()
                .withExternal()
                .source(TaskStateEnum.HANDLING).target(TaskStateEnum.FINISHED)
                .event(TaskEventEnum.FINISH)
                .guard(applicationContext.getBean(FinishGuard.class))
                .action(applicationContext.getBean(FinishAction.class))
                //
                .and()
                .withExternal()
                .source(TaskStateEnum.FINISHED).target(TaskStateEnum.AUDITING)
                .event(TaskEventEnum.EVALUATE)
                .guard(applicationContext.getBean(EvaluateGuard.class))
                .action(applicationContext.getBean(EvaluateAction.class))
                //
                .and()
                .withExternal()
                .source(TaskStateEnum.AUDITING).target(TaskStateEnum.CLOSED)
                .event(TaskEventEnum.PASS_EVALUATE)
                .guard(applicationContext.getBean(PassEvaluateGuard.class))
                .action(applicationContext.getBean(PassEvaluateAction.class))
                //
                .and()
                .withExternal()
                .source(TaskStateEnum.AUDITING).target(TaskStateEnum.HANDLING)
                .event(TaskEventEnum.REJECT_EVALUATE)
                .guard(applicationContext.getBean(PassEvaluateGuard.class))
                .action(applicationContext.getBean(PassEvaluateAction.class))
                //
                .and()
                .withExternal()
                .source(TaskStateEnum.AUDITING).target(TaskStateEnum.TO_ACCEPT)
                .event(TaskEventEnum.REJECT);
    }

    @Override
    public void configure(StateMachineStateConfigurer<TaskStateEnum, TaskEventEnum> states) throws Exception {
        states
                .withStates()
                .initial(TaskStateEnum.CREATED)
                .states(new HashSet<>(EnumSet.allOf(TaskStateEnum.class)));

    }
}
