package com.example.task.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.AbstractEntity;

import com.example.task.enums.TaskStateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 状态机demo任务表
 * </p>
 *
 * @author xh
 * @since 2024-08-18
 */
@Getter
@Setter
@TableName("fsm_task")
@ApiModel(value = "Task对象", description = "状态机demo任务表")
public class Task extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("任务名称")
    @TableField("task_name")
    private String taskName;

    @ApiModelProperty("任务内容描述")
    @TableField("task_content")
    private String taskContent;

    @ApiModelProperty("处理人id")
    @TableField("handler_id")
    private Long handlerId;

    @ApiModelProperty("任务类型")
    @TableField("task_type")
    private Long taskType;

    @ApiModelProperty("任务节点")
    @TableField("task_states")
    private String taskStates;

    @ApiModelProperty("任务状态")
    @TableField("task_status")
    private TaskStateEnum taskStatus;


}
