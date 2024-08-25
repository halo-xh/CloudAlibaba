package com.example.jtademo.task.trans.request;

import com.example.jtademo.task.trans.dto.TaskTypeDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * TaskType创建对象
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "TaskType", description = "状态机demo任务类型表")
public class TaskTypeCreateRequest extends TaskTypeDTO {


    private static final long serialVersionUID = 1L;

}
