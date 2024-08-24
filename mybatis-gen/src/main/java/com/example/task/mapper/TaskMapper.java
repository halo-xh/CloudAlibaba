package com.example.task.mapper;

import com.example.task.entity.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 状态机demo任务表 Mapper 接口
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task> {

}
