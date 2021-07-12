package com.xh.dlq.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@MappedSuperclass
public abstract class LongIdentifier implements Serializable {

    @Id
    @Column(name = "id", nullable = false, length = 20)
    @GeneratedValue(generator = "idCreator")
    @GenericGenerator(name = "idCreator", strategy = "com.xh.dlq.config.IdGenerator")
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
