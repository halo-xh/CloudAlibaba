package com.example.task.service;

import com.example.task.controller.request.TaskCreateRequest;

/**
 * <p>
 * 状态机demo任务表 服务类
 * </p>
 * @author xh
 * @since 2024-08-18
 */
public interface TaskService {

    Long submitTask(TaskCreateRequest request);

    void acceptTask(Long id);

    void rejectTask(Long id);

    void finishTask(Long id);

    void evaluateTask(Long id);

    void passEvaluateTask(Long id);

    void rejectEvaluateTask(Long id);

    void closeTask(Long id);

    void hangUpTask(Long id);

    void hangDownTask(Long id);

    void transTask(Long id);

    void handleTask(Long id);

    void dispatchTask(Long id);

}
