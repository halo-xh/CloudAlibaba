package com.example.task.controller;

import com.example.task.controller.request.TaskCreateRequest;
import com.example.task.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 状态机demo任务表 前端控制器
 * </p>
 *
 * @author xh
 * @since 2024-08-18
 */
@Api(value = "状态机demo任务表", tags = "状态机demo任务表")
@RestController
@RequestMapping("/task/task")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @ApiOperation(value = "创建任务")
    @PostMapping("/create")
    public Long createTask(@RequestBody TaskCreateRequest request) throws Exception {
        return taskService.submitTask(request);
    }

    @ApiOperation(value = "接受任务")
    @PutMapping("/accept/{id}")
    public void acceptTask(@PathVariable(value = "id") Long id) throws Exception {
        taskService.acceptTask(id);
    }

}
