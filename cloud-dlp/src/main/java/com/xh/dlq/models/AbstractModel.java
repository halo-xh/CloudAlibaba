package com.xh.dlq.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractModel extends LongIdentifier {

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '创建人'")
    private String createdBy;

    @Column(columnDefinition = "varchar(100) DEFAULT NULL COMMENT '创建人名称'")
    private String createdByName;

    @CreatedDate
    @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '创建时间'")
    private Date createdAt;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '修改人'")
    private String modifiedBy;

    @Column(columnDefinition = "varchar(255) DEFAULT NULL COMMENT '修改人名称'")
    private String modifiedByName;

    @LastModifiedDate
    @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '修改时间'")
    private Date modifiedAt;

    @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '删除时间'")
    private Date deletedAt;

    @Version
    @Column(columnDefinition = "int(11) NOT NULL COMMENT '版本'")
    private int version;

    @Column(columnDefinition = "bit(1) DEFAULT NULL COMMENT '是否删除'")
    private Boolean isDeleted = Boolean.FALSE;

    /**
     * 是否启用
     */
    @NotNull
    @Column(columnDefinition = "bit(1) NOT NULL DEFAULT 1 COMMENT '是否启用'")
    private Boolean enabled = Boolean.TRUE;

    public void markAsDeleted() {
        this.setIsDeleted(true);
        this.setDeletedAt(new Date());
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isShow() {
        return this.enabled && !this.isDeleted;
    }
}
