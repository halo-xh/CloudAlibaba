package com.example.task.manager.impl;

import com.example.task.entity.TaskStateMachineDefinition;
import com.example.task.manager.TaskStateMachineDefinitionManager;
import com.example.task.mapper.TaskStateMachineDefinitionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fanyi.xh
 * @since 2024-12-24
 */
@Slf4j
@Service
public class TaskStateMachineDefinitionManagerImpl implements TaskStateMachineDefinitionManager {

    @Autowired
    private TaskStateMachineDefinitionMapper taskStateMachineDefinitionMapper;


    @Override
    public TaskStateMachineDefinition findByTaskId(Long taskId) {
        return taskStateMachineDefinitionMapper.findByTaskId(taskId);
    }

    @Override
    public void save(TaskStateMachineDefinition definition) {
        taskStateMachineDefinitionMapper.insert(definition);
    }

}
