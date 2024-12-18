package com.example.task.manager.impl;

import com.example.task.entity.TaskType;
import com.example.task.manager.TaskTypeManager;
import com.example.task.mapper.TaskTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fanyi.xh
 * @since 2024-12-18
 */
@Slf4j
@Service
public class TaskTypeManagerImpl implements TaskTypeManager {


    @Autowired
    private TaskTypeMapper taskTypeMapper;


    @Override
    public TaskType findById(Long taskTypeId) {
        return taskTypeMapper.findById(taskTypeId);
    }
}
