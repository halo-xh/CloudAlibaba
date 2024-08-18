package com.example.task.controller.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AbstractEntityVO {

    private Long id;

    @ApiModelProperty("创建时间")
    private Date createdAt;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("修改时间")
    private Date modifiedAt;

    @ApiModelProperty("修改人")
    private String modifiedBy;

    @ApiModelProperty("是否删除")
    private Boolean isDeleted;

    @ApiModelProperty("版本号")
    private Integer version;

}
