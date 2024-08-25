package com.example.jtademo.task.pg.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.jtademo.common.AbstractEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 状态机demo任务表
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Getter
@Setter
@TableName("fsm_task")
@Schema(name = "Task", description = "状态机demo任务表")
public class PGTask extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "任务名称")
    @TableField("task_name")
    private String taskName;

    @Schema(description = "任务内容描述")
    @TableField("task_content")
    private String taskContent;

    @Schema(description = "任务状态")
    @TableField("task_status")
    private String taskStatus;

    @Schema(description = "处理人id")
    @TableField("handler_id")
    private Long handlerId;

    @Schema(description = "任务类型")
    @TableField("task_type")
    private Long taskType;

    @Schema(description = "任务节点")
    @TableField("task_states")
    private String taskStates;
}
