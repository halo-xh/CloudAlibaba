package com.example.task.trans.request;

import com.example.task.trans.dto.TaskDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * Task更新对象
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "Task更新对象", description = "状态机demo任务表")
public class TaskUpdateRequest extends TaskDTO {


    private static final long serialVersionUID = 1L;

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "乐观锁", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer version;

}
