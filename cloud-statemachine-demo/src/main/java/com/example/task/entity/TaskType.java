package com.example.task.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 状态机demo任务类型表
 * </p>
 *
 * @author xh
 * @since 2024-08-18
 */
@Getter
@Setter
@TableName("fsm_task_type")
@ApiModel(value = "TaskType对象", description = "状态机demo任务类型表")
public class TaskType extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("任务类型名称")
    @TableField("type_name")
    private String typeName;

    @ApiModelProperty("任务类型所需要的节点")
    @TableField("task_states")
    private String taskStates;

    @ApiModelProperty("允许挂起")
    @TableField("allow_huang_up")
    private Boolean allowHuangUp;

    @ApiModelProperty("完成需要审核")
    @TableField("need_audit")
    private Boolean needAudit;

    @ApiModelProperty("手动调派")
    @TableField("need_manual_dispatch")
    private Boolean needManualDispatch;

    @ApiModelProperty("需要用户评价")
    @TableField("need_invite_evaluation")
    private Boolean needInviteEvaluation;


}
