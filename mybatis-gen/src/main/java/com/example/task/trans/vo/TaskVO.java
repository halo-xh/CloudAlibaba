package com.example.task.trans.vo;


import com.example.mybatisgen.AbstractVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * Task VO
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "Task VO", description = "状态机demo任务表")
public class TaskVO extends AbstractVO {

    private static final long serialVersionUID = 1L;

    @Schema(description = "任务名称")
    private String taskName;

    @Schema(description = "任务内容描述")
    private String taskContent;

    @Schema(description = "任务状态")
    private String taskStatus;

    @Schema(description = "处理人id")
    private Long handlerId;

    @Schema(description = "任务类型")
    private Long taskType;

    @Schema(description = "任务节点")
    private String taskStates;

}