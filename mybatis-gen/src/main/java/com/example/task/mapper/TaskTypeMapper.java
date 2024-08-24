package com.example.task.mapper;

import com.example.task.entity.TaskType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 状态机demo任务类型表 Mapper 接口
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Mapper
public interface TaskTypeMapper extends BaseMapper<TaskType> {

}
