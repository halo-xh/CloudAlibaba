package com.example.jtademo.task.trans.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * TaskType对象
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Data
@Schema(name = "TaskType", description = "状态机demo任务类型表")
public class TaskTypeDTO {


    private static final long serialVersionUID = 1L;

    @Schema(description = "任务类型名称", maxLength = 100, nullable = false, defaultValue = "-" )
    private String typeName;

    @Schema(description = "任务类型所需要的节点", maxLength = 2000, nullable = false, defaultValue = "-" )
    private String taskStates;

}
