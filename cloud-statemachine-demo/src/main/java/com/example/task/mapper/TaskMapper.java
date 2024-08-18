package com.example.task.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.task.entity.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 状态机demo任务表 Mapper 接口
 * </p>
 *
 * @author xh
 * @since 2024-08-18
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task> {

    default Task findById(Long taskId){
        return selectOne(Wrappers.<Task>lambdaQuery()
                .eq(Task::getId, taskId)
                .eq(Task::getIsDeleted, 0));
    }

}
