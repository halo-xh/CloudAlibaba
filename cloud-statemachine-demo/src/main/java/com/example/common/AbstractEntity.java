package com.example.common;


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public abstract class AbstractEntity {


    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建时间")
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    @ApiModelProperty("创建人")
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private String createdBy;

    @ApiModelProperty("修改时间")
    @TableField(value = "modified_at", fill = FieldFill.INSERT_UPDATE)
    private Date modifiedAt;

    @ApiModelProperty("修改人")
    @TableField(value = "modified_by", fill = FieldFill.INSERT_UPDATE)
    private String modifiedBy;

    @ApiModelProperty("是否删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty("版本号")
    @TableField("version")
    @Version
    private Integer version;

}
