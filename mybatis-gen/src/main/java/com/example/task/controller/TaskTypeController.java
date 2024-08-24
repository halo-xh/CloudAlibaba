package com.example.task.controller;


import com.example.task.service.TaskTypeService;
import com.example.task.trans.request.TaskTypeCreateRequest;
import com.example.task.trans.vo.TaskTypeVO;
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
 * 状态机demo任务类型表 前端控制器
 * </p>
 *
 * @author xh
 * @since 2024-08-24
 */
@Tag(name = "状态机demo任务类型表接口", description = "状态机demo任务类型表接口")
@RestController
@RequestMapping("/task/taskType")

public class TaskTypeController {

    @Autowired
    private TaskTypeService taskTypeService;


    @Operation(summary = "创建", description = "创建")
    @PostMapping("/create")
    public TaskTypeVO create(@RequestBody TaskTypeCreateRequest request) {
        return taskTypeService.create(request);
    }


    @Operation(summary = "详情", description = "详情")
    @Parameters({
    @Parameter(name = "id", description = "id", required = true, in = ParameterIn.PATH)
    })
    @GetMapping("/detail/{id}")
    public TaskTypeVO detail(@PathVariable(value = "id") Long id) throws Exception {
        return taskTypeService.detail(id);
    }

}
