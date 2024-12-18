package com.example.task.manager;

import com.example.task.entity.Task;
import com.example.task.enums.TaskStateEnum;

/**
 * @author fanyi.xh
 * @since 2024-12-18
 */
public interface TaskManager {

    Task findById(Long id);

    boolean updateStatus(Long id, TaskStateEnum stateEnum);

    void create(Task task);
}
