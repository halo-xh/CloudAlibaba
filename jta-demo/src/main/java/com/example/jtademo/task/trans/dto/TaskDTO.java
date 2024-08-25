package com.example.jtademo.task.trans.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * Task对象
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Data
@Schema(name = "Task", description = "状态机demo任务表")
public class TaskDTO {


    private static final long serialVersionUID = 1L;

    @Schema(description = "任务名称", maxLength = 100, nullable = true, defaultValue = "-" )
    private String taskName;

    @Schema(description = "任务内容描述", maxLength = 65535, nullable = true, defaultValue = "-" )
    private String taskContent;

    @Schema(description = "任务状态", maxLength = 100, nullable = false, defaultValue = "-" )
    private String taskStatus;

    @Schema(description = "处理人id", maxLength = 19, nullable = true, defaultValue = "-" )
    private Long handlerId;

    @Schema(description = "任务类型", maxLength = 19, nullable = false, defaultValue = "-" )
    private Long taskType;

    @Schema(description = "任务节点", maxLength = 2000, nullable = false, defaultValue = "-" )
    private String taskStates;

}
