package com.example.jtademo.task.pg.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.jtademo.task.pg.entity.PGTask;
import com.example.jtademo.task.trans.request.TaskCreateRequest;
import com.example.jtademo.task.trans.vo.TaskVO;

/**
 * <p>
 * 状态机demo任务表 服务类
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
public interface PGTaskService extends IService<PGTask> {

    TaskVO detail(Long id);

    TaskVO create(TaskCreateRequest request);

    void create(PGTask pgTask);
}
