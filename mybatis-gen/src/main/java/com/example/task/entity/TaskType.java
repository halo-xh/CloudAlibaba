package com.example.task.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.mybatisgen.AbstractEntity;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 状态机demo任务类型表
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Getter
@Setter
@TableName("fsm_task_type")
@Schema(name = "TaskType", description = "状态机demo任务类型表")
public class TaskType extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "任务类型名称")
    @TableField("type_name")
    private String typeName;

    @Schema(description = "任务类型所需要的节点")
    @TableField("task_states")
    private String taskStates;
}
