package com.example.task.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author fanyi.xh
 * @since 2024-12-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("task_state_machine_definition")
public class TaskStateMachineDefinition extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long taskId;

    private String definition;


}
