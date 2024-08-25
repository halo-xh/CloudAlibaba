package com.example.jtademo.task.mysql.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.jtademo.task.mysql.entity.Task;
import com.example.jtademo.task.trans.request.TaskCreateRequest;
import com.example.jtademo.task.trans.vo.TaskVO;

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

    void create(Task task);
}
