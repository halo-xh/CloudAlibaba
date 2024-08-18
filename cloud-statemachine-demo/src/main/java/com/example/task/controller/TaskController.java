package com.example.task.controller;

import com.example.task.controller.request.TaskCreateRequest;
import com.example.task.controller.response.TaskVO;
import com.example.task.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 状态机demo任务表 前端控制器
 * </p>
 *
 * @author xh
 * @since 2024-08-18
 */
@RestController
@RequestMapping("/task/task")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @ApiOperation(value = "创建任务")
    @PostMapping("/create")
    public Long createTask(@RequestBody TaskCreateRequest request) throws Exception {
        return taskService.createTask(request);
    }

    @ApiOperation(value = "接受任务")
    @PutMapping("/accept/{id}")
    public void acceptTask(@PathVariable(value = "id") Long id) throws Exception {
        taskService.acceptTask(id);
    }

}
