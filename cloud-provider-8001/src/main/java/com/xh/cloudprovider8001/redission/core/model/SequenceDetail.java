package com.xh.cloudprovider8001.redission.core.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 编号生成表
 * </p>
 *
 * @author Xiao Hong
 * @since 2022-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "cm_sn_generate")
@ApiModel(value = "CmSnGenerate对象", description = "编号生成表")
public class SequenceDetail {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "业务类型，唯一")
    private String bizType;

    @ApiModelProperty(value = "最大值")
    private Long maxVal;

    @ApiModelProperty(value = "步长")
    private Integer step;

    @ApiModelProperty(value = "余数")
    private Long current = 0L;

    @Version
    @ApiModelProperty(value = "版本")
    private Long version;

    public static final Integer DELETE_TRUE = 0;
    public static final Integer DELETE_FALSE = 1;

    private Long createUserId;

    private Long updateUserId;

    @TableField(fill = FieldFill.INSERT)
    private Date serverCreateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date serverUpdateTime;

    private Integer statusFlag = DELETE_FALSE;

}
