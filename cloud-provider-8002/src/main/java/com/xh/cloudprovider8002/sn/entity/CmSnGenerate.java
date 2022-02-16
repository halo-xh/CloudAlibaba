package com.xh.cloudprovider8002.sn.entity;


import com.xh.cloudprovider8002.sn.idgen.model.SequenceAcquireLock;
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
@ApiModel(value="CmSnGenerate对象", description="编号生成表")
public class CmSnGenerate extends SequenceAcquireLock {

    @ApiModelProperty(value = "业务类型，唯一")
    private String bizType;

    @ApiModelProperty(value = "最大值")
    private Long max;

    @ApiModelProperty(value = "步长")
    private Integer step;

    @ApiModelProperty(value = "余数")
    private Long current;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "创建人id")
    private Long createUserId;

    @ApiModelProperty(value = "修改人id")
    private Long updateUserId;

    @ApiModelProperty(value = "服务器创建时间")
    private Date serverCreateTime;

    @ApiModelProperty(value = "服务器最后修改时间")
    private Date serverUpdateTime;

    @ApiModelProperty(value = "删除标记，1:未删除，0:删除")
    private Boolean statusFlag;


}
