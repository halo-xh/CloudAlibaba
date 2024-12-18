package com.example.task.manager.impl;

import com.example.task.entity.Task;
import com.example.task.enums.TaskStateEnum;
import com.example.task.manager.TaskManager;
import com.example.task.mapper.TaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author fanyi.xh
 * @since 2024-12-18
 */
@Slf4j
@Service
public class TaskManagerImpl implements TaskManager {


    @Autowired
    private TaskMapper taskMapper;


    @Override
    public Task findById(Long id) {
        return taskMapper.findById(id);
    }

    @Override
    public boolean updateStatus(Long id, TaskStateEnum stateEnum) {
        Task task = taskMapper.findById(id);
        Assert.notNull(task, "task is null");
        task.setTaskStatus(stateEnum);
        int i = taskMapper.updateById(task);
        return i > 0;
    }


    @Override
    public void create(Task task) {
        taskMapper.insert(task);
    }


}
