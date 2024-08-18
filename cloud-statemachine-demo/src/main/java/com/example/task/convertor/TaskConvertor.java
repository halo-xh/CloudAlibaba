package com.example.task.convertor;


import com.example.task.controller.request.TaskCreateRequest;
import com.example.task.controller.response.TaskVO;
import com.example.task.entity.Task;
import org.mapstruct.Mapper;

@Mapper
public interface TaskConvertor {


    TaskVO toVO(Task task);

    Task toTask(TaskCreateRequest request);


}
