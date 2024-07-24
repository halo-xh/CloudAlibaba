package com.example.cloudsimple.jetcache;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "testdo")
public class TestDO2 implements Serializable {

    @TableId
    private Long id;

    private String name;

    private Integer value;

    private String createdBy;

    private Date createdAt;

    private String modifiedBy;

    private Date modifiedAt;

    private Integer version;

    private Boolean isDeleted = Boolean.FALSE;


}
