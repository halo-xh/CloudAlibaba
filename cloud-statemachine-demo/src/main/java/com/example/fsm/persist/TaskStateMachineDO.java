package com.example.fsm.persist;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.AbstractEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Xiao Hong
 * @since 2024-12-16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("fsm_state_machine")
@ApiModel(value = "状态机对象", description = "状态机对象")
public class TaskStateMachineDO extends AbstractEntity {

    private Long id;
    private String machineId;
    private String state;
    private byte[] stateMachineContext;

}
