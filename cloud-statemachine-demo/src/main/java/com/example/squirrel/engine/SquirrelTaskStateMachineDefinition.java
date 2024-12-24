package com.example.squirrel.engine;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fanyi.xh
 * @since 2024-12-24
 */
@Data
public class SquirrelTaskStateMachineDefinition {

    @ApiModelProperty("任务类型所需要的节点")
    private String taskStates;

    @ApiModelProperty("允许挂起")
    private Boolean allowHuangUp;

    @ApiModelProperty("完成需要审核")
    private Boolean needAudit;

    @ApiModelProperty("手动调派")
    private Boolean needManualDispatch;

    @ApiModelProperty("需要用户评价")
    private Boolean needInviteEvaluation;

}
