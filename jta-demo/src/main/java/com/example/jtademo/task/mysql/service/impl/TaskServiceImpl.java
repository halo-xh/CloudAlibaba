package com.example.jtademo.task.mysql.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.jtademo.task.mysql.entity.Task;
import com.example.jtademo.task.mysql.mapper.TaskMapper;
import com.example.jtademo.task.pg.mapper.PGTaskMapper;
import com.example.jtademo.task.mysql.service.TaskService;
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
@DS(value = "mysql")
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {


    @Autowired
    private TaskMapper taskMapper;

    @Override
    public TaskVO detail(Long id) {
        return null;
    }


    @Transactional
    @Override
    public TaskVO create(TaskCreateRequest request) {
        return null;
    }

    @Override
    public void create(Task task) {
        taskMapper.insert(task);
    }

}
