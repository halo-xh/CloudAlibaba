package com.example.fsm.persist;

import com.example.fsm.event.TaskEventEnum;
import com.example.task.enums.TaskStateEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.service.StateMachineSerialisationService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author Xiao Hong
 * @since 2024-12-17
 */
@Slf4j
@Service
public class TaskStateMachineSerialisationService implements StateMachineSerialisationService<TaskStateEnum, TaskEventEnum> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialiseStateMachineContext(StateMachineContext context) throws Exception {
        String value = objectMapper.writeValueAsString(context);
        return value.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public StateMachineContext<TaskStateEnum, TaskEventEnum> deserialiseStateMachineContext(byte[] data) throws Exception {
        StateMachineContext<TaskStateEnum, TaskEventEnum> value = objectMapper.readValue(data, StateMachineContext.class);
        return value;
    }

}
