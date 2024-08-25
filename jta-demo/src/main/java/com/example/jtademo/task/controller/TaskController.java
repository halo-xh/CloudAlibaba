package com.example.jtademo.task.controller;


import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.example.jtademo.task.mysql.entity.Task;
import com.example.jtademo.task.pg.entity.PGTask;
import com.example.jtademo.task.mysql.service.TaskService;
import com.example.jtademo.task.pg.service.PGTaskService;
import com.example.jtademo.task.trans.request.TaskCreateRequest;
import com.example.jtademo.task.trans.vo.TaskVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 状态机demo任务表 前端控制器
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Tag(name = "状态机demo任务表接口", description = "状态机demo任务表接口")
@RestController
@RequestMapping("/task/task")

public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private PGTaskService pgTaskService;



    @DSTransactional(rollbackFor = Exception.class)
    @Operation(summary = "创建", description = "创建")
    @PostMapping("/create")
    public TaskVO create(@RequestBody TaskCreateRequest request) {
        Task task = new Task();
        task.setTaskName(request.getTaskName());
        task.setTaskType(request.getTaskType());
        task.setTaskContent(request.getTaskContent());
        task.setTaskStatus(request.getTaskStatus());
        task.setHandlerId(request.getHandlerId());
        task.setTaskStates(request.getTaskStates());
        taskService.create(task);

        PGTask pgTask = new PGTask();
        pgTask.setTaskName(request.getTaskName());
        pgTask.setTaskType(request.getTaskType());
        pgTask.setTaskContent(request.getTaskContent());
        pgTask.setTaskStatus(request.getTaskStatus());
        pgTask.setHandlerId(request.getHandlerId());
        pgTask.setTaskStates(request.getTaskStates());
        pgTaskService.create(pgTask);

        int a = 1/0;
        TaskVO taskVO = new TaskVO();
        BeanUtils.copyProperties(task, taskVO);
        return taskVO;
    }


    @Operation(summary = "详情", description = "详情")
    @Parameters({
    @Parameter(name = "id", description = "id", required = true, in = ParameterIn.PATH)
    })
    @GetMapping("/detail/{id}")
    public TaskVO detail(@PathVariable(value = "id") Long id) throws Exception {
        return taskService.detail(id);
    }

}
