package com.example.task.entity;

import lombok.Data;

/**
 * @author fanyi.xh
 * @since 2024-12-23
 */
@Data
public class TaskStateMachineDefinition {

    // 允许挂起
    private Boolean allowHangUp;

    // 是否需要审核
    private Boolean needAudit;

    // 需要手动调派
    private Boolean needManualDispatch;


}
