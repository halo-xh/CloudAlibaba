package com.example.task.manager;

import com.example.task.entity.TaskStateMachineDefinition;

/**
 * @author fanyi.xh
 * @since 2024-12-24
 */
public interface TaskStateMachineDefinitionManager {

    TaskStateMachineDefinition findByTaskId(Long taskId);


    void save(TaskStateMachineDefinition definition);
}
