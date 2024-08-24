package com.example.task.trans.request;

import com.example.task.trans.dto.TaskTypeDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * TaskType更新对象
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "TaskType更新对象", description = "状态机demo任务类型表")
public class TaskTypeUpdateRequest extends TaskTypeDTO {


    private static final long serialVersionUID = 1L;

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "乐观锁", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer version;

}
