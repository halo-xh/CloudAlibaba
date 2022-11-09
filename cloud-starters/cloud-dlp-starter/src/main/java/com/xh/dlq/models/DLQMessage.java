package com.xh.dlq.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Table(name = "dlq_msg")
public class DLQMessage extends AbstractModel {

    private String originalTopic;

    private String businessEvent;

    @Lob
    @Column(columnDefinition = "text")
    private String payload;

    @Lob
    @Column(columnDefinition = "text")
    private String exceptionMessage;

    @Lob
    @Column(columnDefinition = "text")
    private String exceptionStacktrace;

    private Boolean handled = Boolean.FALSE;

    private Date handleAt;

    private Boolean deleted = Boolean.FALSE;

}
