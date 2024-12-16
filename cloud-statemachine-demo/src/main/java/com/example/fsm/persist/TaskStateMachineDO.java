package com.example.fsm.persist;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Xiao Hong
 * @since 2024-12-16
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class TaskStateMachineDO  {

    private String id;
    private String machineId;
    private String state;
    private String stateMachineContext;

}
