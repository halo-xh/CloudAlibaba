package com.example.fsm.persist.infra;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fsm.persist.TaskStateMachineDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 状态机demo任务类型表 Mapper 接口
 * </p>
 *
 * @author xh
 * @since 2024-08-18
 */
@Mapper
public interface StateMachineMapper extends BaseMapper<TaskStateMachineDO> {


}
