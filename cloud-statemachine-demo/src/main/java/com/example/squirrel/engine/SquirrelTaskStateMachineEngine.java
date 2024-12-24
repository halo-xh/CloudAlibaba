package com.example.squirrel.engine;

import com.example.squirrel.condition.Accepted_2_Handling_Condition;
import com.example.squirrel.condition.Auditing_2_Closed_Condition;
import com.example.squirrel.condition.Auditing_2_Handling_Condition;
import com.example.squirrel.condition.Create_2_ToDispatch_Condition;
import com.example.squirrel.condition.Finished_2_Auditing_Condition;
import com.example.squirrel.condition.Handling_2_Finished_Condition;
import com.example.squirrel.condition.Handling_2_Suspend_Condition;
import com.example.squirrel.condition.Suspend_2_Handling_Condition;
import com.example.squirrel.condition.ToAccept_2_Accepted_Condition;
import com.example.squirrel.condition.ToDispatch_2_ToAccept_Condition;
import com.example.squirrel.context.SquirrelTaskContext;
import com.example.squirrel.event.SquirrelTaskEvent;
import com.example.squirrel.machine.SquirrelTaskStateMachine;
import com.example.squirrel.perform.Accepted_2_Handling_Perform;
import com.example.squirrel.perform.Auditing_2_Closed_Perform;
import com.example.squirrel.perform.Auditing_2_Handling_Perform;
import com.example.squirrel.perform.Create_2_ToDispatch_Perform;
import com.example.squirrel.perform.Finished_2_Auditing_Perform;
import com.example.squirrel.perform.Handling_2_Finished_Perform;
import com.example.squirrel.perform.Handling_2_Suspend_Perform;
import com.example.squirrel.perform.Suspend_2_Handling_Perform;
import com.example.squirrel.perform.ToAccept_2_Accepted_Perform;
import com.example.squirrel.perform.ToDispatch_2_ToAccept_Perform;
import com.example.task.entity.Task;
import com.example.task.entity.TaskStateMachineDefinition;
import com.example.task.enums.TaskStateEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.squirrelframework.foundation.fsm.StateMachineBuilder;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.StateMachineConfiguration;
import org.squirrelframework.foundation.fsm.StateMachinePerformanceMonitor;

/**
 * @author fanyi.xh
 * @since 2024-12-19
 */
@Slf4j
@Component
public class SquirrelTaskStateMachineEngine {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ApplicationContext applicationContext;

    @SneakyThrows
    public void createAndFire(SquirrelTaskContext context, SquirrelTaskEvent event) {

        TaskStateMachineDefinition machineDefinition = context.getMachineDefinition();
        Assert.notNull(machineDefinition, "machineDefinition is null");
        String definition = machineDefinition.getDefinition();
        Assert.hasText(definition, "definition is null");
        SquirrelTaskStateMachineDefinition def = objectMapper.readValue(definition, SquirrelTaskStateMachineDefinition.class);

        StateMachineBuilder<SquirrelTaskStateMachine, TaskStateEnum, SquirrelTaskEvent, SquirrelTaskContext> builder =
                StateMachineBuilderFactory.create(SquirrelTaskStateMachine.class, TaskStateEnum.class, SquirrelTaskEvent.class, SquirrelTaskContext.class);

        builder.defineStartEvent(SquirrelTaskEvent.SUBMIT);

        builder.externalTransition()
                .from(TaskStateEnum.CREATED).to(TaskStateEnum.TO_DISPATCH)
                .on(SquirrelTaskEvent.SUBMIT).when(applicationContext.getBean(Create_2_ToDispatch_Condition.class))
                .perform(applicationContext.getBean(Create_2_ToDispatch_Perform.class));

        builder.externalTransition()
                .from(TaskStateEnum.TO_DISPATCH).to(TaskStateEnum.TO_ACCEPT)
                .on(SquirrelTaskEvent.DISPATCHED).when(applicationContext.getBean(ToDispatch_2_ToAccept_Condition.class))
                .perform(applicationContext.getBean(ToDispatch_2_ToAccept_Perform.class));

        builder.externalTransition()
                .from(TaskStateEnum.TO_ACCEPT).to(TaskStateEnum.ACCEPTED)
                .on(SquirrelTaskEvent.ACCEPT).when(applicationContext.getBean(ToAccept_2_Accepted_Condition.class))
                .perform(applicationContext.getBean(ToAccept_2_Accepted_Perform.class));

        builder.externalTransition()
                .from(TaskStateEnum.ACCEPTED).to(TaskStateEnum.HANDLING)
                .on(SquirrelTaskEvent.HANDLE).when(applicationContext.getBean(Accepted_2_Handling_Condition.class))
                .perform(applicationContext.getBean(Accepted_2_Handling_Perform.class));

        builder.externalTransition()
                .from(TaskStateEnum.HANDLING).to(TaskStateEnum.FINISHED)
                .on(SquirrelTaskEvent.FINISH).when(applicationContext.getBean(Handling_2_Finished_Condition.class))
                .perform(applicationContext.getBean(Handling_2_Finished_Perform.class));

        // 挂起
        if (Boolean.TRUE.equals(def.getAllowHuangUp())) {
            builder.externalTransition()
                    .from(TaskStateEnum.HANDLING).to(TaskStateEnum.SUSPEND)
                    .on(SquirrelTaskEvent.HANG_UP).when(applicationContext.getBean(Handling_2_Suspend_Condition.class))
                    .perform(applicationContext.getBean(Handling_2_Suspend_Perform.class));

            builder.externalTransition()
                    .from(TaskStateEnum.SUSPEND).to(TaskStateEnum.HANDLING)
                    .on(SquirrelTaskEvent.HANG_DOWN).when(applicationContext.getBean(Suspend_2_Handling_Condition.class))
                    .perform(applicationContext.getBean(Suspend_2_Handling_Perform.class));
        }

        // 审核
        if (Boolean.TRUE.equals(def.getNeedAudit())) {
            builder.externalTransition()
                    .from(TaskStateEnum.FINISHED).to(TaskStateEnum.AUDITING)
                    .on(SquirrelTaskEvent.EVALUATE).when(applicationContext.getBean(Finished_2_Auditing_Condition.class))
                    .perform(applicationContext.getBean(Finished_2_Auditing_Perform.class));

            builder.externalTransition()
                    .from(TaskStateEnum.AUDITING).to(TaskStateEnum.CLOSED)
                    .on(SquirrelTaskEvent.PASS_EVALUATE).when(applicationContext.getBean(Auditing_2_Closed_Condition.class))
                    .perform(applicationContext.getBean(Auditing_2_Closed_Perform.class));

            builder.externalTransition()
                    .from(TaskStateEnum.AUDITING).to(TaskStateEnum.HANDLING)
                    .on(SquirrelTaskEvent.REJECT_EVALUATE).when(applicationContext.getBean(Auditing_2_Handling_Condition.class))
                    .perform(applicationContext.getBean(Auditing_2_Handling_Perform.class));
        }

        builder.defineTerminateEvent(SquirrelTaskEvent.CLOSE);
        builder.defineFinalState(TaskStateEnum.CLOSED);

        Task task = context.getTask();
        Assert.notNull(task, "task is null");
        SquirrelTaskStateMachine newedStateMachine = builder.newStateMachine(task.getTaskStatus(),
                StateMachineConfiguration.create()
                        .enableDebugMode(false)
                        .enableAutoStart(true));
        StateMachinePerformanceMonitor stateMachinePerformanceMonitor = new StateMachinePerformanceMonitor("task fsm: " + task.getId());
        newedStateMachine.addDeclarativeListener(stateMachinePerformanceMonitor);
        newedStateMachine.fire(event, context);
        newedStateMachine.terminate();
        log.info("\n" + stateMachinePerformanceMonitor.getPerfModel());
    }


}
