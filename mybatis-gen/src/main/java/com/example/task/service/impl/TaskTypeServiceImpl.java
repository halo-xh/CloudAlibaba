package com.example.task.service.impl;

import com.example.task.entity.TaskType;
import com.example.task.mapper.TaskTypeMapper;
import com.example.task.service.TaskTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.task.trans.request.TaskTypeCreateRequest;
import com.example.task.trans.vo.TaskTypeVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 状态机demo任务类型表 服务实现类
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Service
public class TaskTypeServiceImpl extends ServiceImpl<TaskTypeMapper, TaskType> implements TaskTypeService {

    @Override
    public TaskTypeVO create(TaskTypeCreateRequest request) {
        return null;
    }

    @Override
    public TaskTypeVO detail(Long id) {
        return null;
    }
}
