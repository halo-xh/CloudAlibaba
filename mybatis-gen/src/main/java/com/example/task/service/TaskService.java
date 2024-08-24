package com.example.task.service;

import com.example.task.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.task.trans.request.TaskCreateRequest;
import com.example.task.trans.vo.TaskVO;

/**
 * <p>
 * 状态机demo任务表 服务类
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
public interface TaskService extends IService<Task> {

    TaskVO detail(Long id);

    TaskVO create(TaskCreateRequest request);
}
