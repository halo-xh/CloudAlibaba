package com.example.task.service;

import com.example.task.entity.TaskType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.task.trans.request.TaskTypeCreateRequest;
import com.example.task.trans.vo.TaskTypeVO;

/**
 * <p>
 * 状态机demo任务类型表 服务类
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
public interface TaskTypeService extends IService<TaskType> {

    TaskTypeVO create(TaskTypeCreateRequest request);

    TaskTypeVO detail(Long id);
}
