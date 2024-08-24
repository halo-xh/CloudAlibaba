package com.example.task.trans.vo;


import com.example.mybatisgen.AbstractVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * TaskType VO
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "TaskType VO", description = "状态机demo任务类型表")
public class TaskTypeVO extends AbstractVO {

    private static final long serialVersionUID = 1L;

    @Schema(description = "任务类型名称")
    private String typeName;

    @Schema(description = "任务类型所需要的节点")
    private String taskStates;

}