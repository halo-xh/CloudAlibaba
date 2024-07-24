package com.example.cloudsimple.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class TestDO {


    @Id
    @Column(name = "id", nullable = false, length = 20)
    @GenericGenerator(name = "idCreator", strategy = "com.xh.dlq.config.IdGenerator")
    Long id;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '人'")
    String name;

    @Column(columnDefinition = "int(11) NOT NULL COMMENT 'val'")
    Integer value;


    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '创建人'")
    private String createdBy;

    @CreatedDate
    @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '创建时间'")
    private Date createdAt;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '修改人'")
    private String modifiedBy;

    @LastModifiedDate
    @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '修改时间'")
    private Date modifiedAt;

    @Version
    @Column(columnDefinition = "int NOT NULL DEFAULT '0' COMMENT '版本'")
    private Integer version;

    @Column(columnDefinition = "bit(1) DEFAULT NULL COMMENT '是否删除'")
    private Boolean isDeleted = Boolean.FALSE;


}
