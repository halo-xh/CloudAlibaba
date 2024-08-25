package com.example.jtademo.task.mysql.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jtademo.task.mysql.entity.Task;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 状态机demo任务表 Mapper 接口
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@DS(value = "mysql")
@Mapper
public interface TaskMapper extends BaseMapper<Task> {

}
