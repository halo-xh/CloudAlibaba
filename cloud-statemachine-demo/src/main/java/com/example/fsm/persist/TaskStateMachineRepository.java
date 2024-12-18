package com.example.fsm.persist;

import com.baomidou.mybatisplus.extension.service.IService;


/**
 * @author Xiao Hong
 * @since 2024-12-16
 */
public interface TaskStateMachineRepository extends IService<TaskStateMachineDO> {

    TaskStateMachineDO findByMachineId(String machineId);
}
