package com.example.fsm.persist;

import com.example.fsm.event.TaskEventEnum;
import com.example.task.enums.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.kryo.KryoStateMachineSerialisationService;
import org.springframework.statemachine.service.StateMachineSerialisationService;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiao Hong
 * @since 2024-12-16
 */
@Slf4j
@Service
public class TaskStateMachineStateMachinePersister implements StateMachinePersist<TaskStateEnum, TaskEventEnum, Object> {


    @Autowired
    private TaskStateMachineRepository taskStateMachineRepository;

    private final StateMachineSerialisationService<TaskStateEnum, TaskEventEnum> serialisationService = new KryoStateMachineSerialisationService<>();


    public void write(StateMachineContext<TaskStateEnum, TaskEventEnum> context, Object contextObj) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Persisting context " + context + " using contextObj " + contextObj);
        }
        TaskStateMachineDO build = build(context, contextObj, serialisationService.serialiseStateMachineContext(context));
        taskStateMachineRepository.save(build);
    }

    @Override
    public StateMachineContext<TaskStateEnum, TaskEventEnum> read(Object contextObj) throws Exception {
        TaskStateMachineDO repositoryStateMachine = taskStateMachineRepository.findById(contextObj.toString());
        // use child contexts if we have those, otherwise fall back to child context refs.
        if (repositoryStateMachine != null) {
            StateMachineContext<TaskStateEnum, TaskEventEnum> context = serialisationService
                    .deserialiseStateMachineContext(repositoryStateMachine.getStateMachineContext().getBytes());
            ;
            if (context != null && context.getChilds() != null && context.getChilds().isEmpty()
                    && context.getChildReferences() != null) {
                List<StateMachineContext<TaskStateEnum, TaskEventEnum>> contexts = new ArrayList<>();
                for (String childRef : context.getChildReferences()) {
                    repositoryStateMachine = taskStateMachineRepository.findById(childRef);
                    if (repositoryStateMachine != null) {
                        contexts.add(serialisationService.deserialiseStateMachineContext(repositoryStateMachine.getStateMachineContext().getBytes()));
                    }
                }
                return new DefaultStateMachineContext<>(contexts, context.getState(), context.getEvent(),
                        context.getEventHeaders(), context.getExtendedState(), context.getHistoryStates(),
                        context.getId());
            } else {
                return context;
            }

        }
        return null;
    }

    protected TaskStateMachineDO build(StateMachineContext<TaskStateEnum, TaskEventEnum> context, Object contextObj, byte[] serialisedContext) {
        TaskStateMachineDO taskStateMachineDO = new TaskStateMachineDO();
        taskStateMachineDO.setId(contextObj.toString());
        taskStateMachineDO.setMachineId(context.getId());
        taskStateMachineDO.setState(context.getState().toString());
        taskStateMachineDO.setStateMachineContext(new String(serialisedContext));
        return taskStateMachineDO;
    }
}
