package com.example.fsm.persist;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Xiao Hong
 * @since 2024-12-16
 */
@Slf4j
@Service
public class TaskStateMachineRepository {

    Map<String, TaskStateMachineDO> map = new ConcurrentHashMap<>();


    public void save(TaskStateMachineDO taskStateMachineDO) {
        map.put(taskStateMachineDO.getId(), taskStateMachineDO);
    }

    public TaskStateMachineDO findById(String id) {
        return map.get(id);
    }
}
