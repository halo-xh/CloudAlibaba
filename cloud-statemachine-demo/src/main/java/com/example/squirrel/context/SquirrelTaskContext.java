package com.example.squirrel.context;

import com.example.task.entity.Task;
import com.example.task.entity.TaskStateMachineDefinition;
import com.example.task.entity.TaskType;
import lombok.Data;

/**
 * @author fanyi.xh
 * @since 2024-12-19
 */
@Data
public class SquirrelTaskContext {

    private Task task;

    private TaskType taskType;

    private TaskStateMachineDefinition machineDefinition;

}
