package com.example.task.service;

import com.example.task.controller.request.TaskCreateRequest;

/**
 * <p>
 * 状态机demo任务表 服务类
 * </p>
 *
 * @author xh
 * @since 2024-08-18
 */
public interface TaskService {

    Long createTask(TaskCreateRequest request) throws Exception;

    void acceptTask(Long id) throws Exception;
}
