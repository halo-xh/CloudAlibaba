package com.example.task.controller.response;


import com.example.task.enums.TaskStateEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TaskVO extends AbstractEntityVO {

    @ApiModelProperty("任务名称")
    private String taskName;

    @ApiModelProperty("任务内容描述")
    private String taskContent;

    @ApiModelProperty("处理人id")
    private Long handlerId;

    @ApiModelProperty("任务类型")
    private Long taskType;

    @ApiModelProperty("任务节点")
    private String taskStates;

    @ApiModelProperty("任务状态")
    private String taskStatus;
}
