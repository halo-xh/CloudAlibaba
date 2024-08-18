package com.example.task.controller.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TaskCreateRequest {

    @ApiModelProperty("任务名称")
    private String taskName;

    @ApiModelProperty("任务内容描述")
    private String taskContent;

    @ApiModelProperty("处理人id")
    private Long handlerId;

    @ApiModelProperty("任务类型")
    private Long taskType;


}
