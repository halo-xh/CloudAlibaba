package com.example.jtademo.task.trans.request;

import com.example.jtademo.task.trans.dto.TaskDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * Task创建对象
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "Task", description = "状态机demo任务表")
public class TaskCreateRequest extends TaskDTO {


    private static final long serialVersionUID = 1L;

}
