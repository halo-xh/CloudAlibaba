package com.xh.clouddubbo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DubboTest implements Serializable {


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "事件类型")
    private String eventType;

    @ApiModelProperty(value = "实例对象ID")
    private Long instanceId;

}
