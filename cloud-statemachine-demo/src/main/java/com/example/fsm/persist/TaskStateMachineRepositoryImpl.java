package com.example.fsm.persist;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fsm.persist.infra.StateMachineMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author fanyi.xh
 * @since 2024-12-18
 */
@Slf4j
@Service
public class TaskStateMachineRepositoryImpl extends ServiceImpl<StateMachineMapper, TaskStateMachineDO> implements TaskStateMachineRepository {

    @Override
    public TaskStateMachineDO findByMachineId(String machineId) {
        return getOne(Wrappers.<TaskStateMachineDO>lambdaQuery()
                .eq(TaskStateMachineDO::getMachineId, machineId)
                .eq(TaskStateMachineDO::getIsDeleted, 0)
                .orderByDesc(TaskStateMachineDO::getId)
                .last("LIMIT 1")
        );
    }
}
