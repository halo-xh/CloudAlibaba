package com.example.jtademo.task.pg.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.jtademo.task.mysql.entity.Task;
import com.example.jtademo.task.mysql.mapper.TaskMapper;
import com.example.jtademo.task.pg.entity.PGTask;
import com.example.jtademo.task.pg.mapper.PGTaskMapper;
import com.example.jtademo.task.pg.service.PGTaskService;
import com.example.jtademo.task.trans.request.TaskCreateRequest;
import com.example.jtademo.task.trans.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 状态机demo任务表 服务实现类
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@DS(value = "postgres")
@Service
public class PGTaskServiceImpl extends ServiceImpl<PGTaskMapper, PGTask> implements PGTaskService {

    @Autowired
    private PGTaskMapper pgTaskMapper;

    @Override
    public TaskVO detail(Long id) {
        return null;
    }


    @Override
    public TaskVO create(TaskCreateRequest request) {
        return null;
    }

    @Override
    public void create(PGTask pgTask) {
        pgTaskMapper.insert(pgTask);
    }

}
