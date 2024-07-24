package com.example.javersdemo.domain;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import java.util.Date;

@Data
@ToString
public abstract class AbstractEntity {


    @Id
    @org.javers.core.metamodel.annotation.Id
    private Long id;

    private Integer version;

    private Date createdAt;

    private String createdBy;

    private Boolean isDeleted;

    private Date modifiedAt;

    private String modifiedBy;

}
