package com.example.task.service.impl;

import com.example.task.entity.TaskType;
import com.example.task.mapper.TaskTypeMapper;
import com.example.task.service.TaskTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 状态机demo任务类型表 服务实现类
 * </p>
 *
 * @author xh
 * @since 2024-08-18
 */
@Service
public class TaskTypeServiceImpl extends ServiceImpl<TaskTypeMapper, TaskType> implements TaskTypeService {

}
