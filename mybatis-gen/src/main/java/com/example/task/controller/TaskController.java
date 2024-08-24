package com.example.task.controller;


import com.example.task.service.TaskService;
import com.example.task.trans.request.TaskCreateRequest;
import com.example.task.trans.vo.TaskVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Operation(summary = "创建", description = "创建")
    @PostMapping("/create")
    public TaskVO create(@RequestBody TaskCreateRequest request) {
        return taskService.create(request);
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
