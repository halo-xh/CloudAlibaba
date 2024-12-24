package com.example.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.task.entity.TaskStateMachineDefinition;
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
public interface TaskStateMachineDefinitionMapper extends BaseMapper<TaskStateMachineDefinition> {

    default TaskStateMachineDefinition findByTaskId(Long taskId) {
        return selectOne(Wrappers.<TaskStateMachineDefinition>lambdaQuery()
                .eq(TaskStateMachineDefinition::getTaskId, taskId)
                .eq(TaskStateMachineDefinition::getIsDeleted, 0));
    }

}
