package com.example.task.service.impl;

import com.example.task.entity.Task;
import com.example.task.mapper.TaskMapper;
import com.example.task.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.task.trans.request.TaskCreateRequest;
import com.example.task.trans.vo.TaskVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 状态机demo任务表 服务实现类
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Override
    public TaskVO detail(Long id) {
        return null;
    }

    @Override
    public TaskVO create(TaskCreateRequest request) {
        return null;
    }
}
