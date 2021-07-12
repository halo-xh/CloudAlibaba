package com.xh.dlq.dto;

import com.xh.dlq.models.DLQMessage;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class DLQMessageSummary {

    private String originalTopic;

    private String payload;

    private String businessEvent;

    private String exceptionMessage;

    private String exceptionStacktrace;

    private Boolean handled;

    private Date handleAt;

    public static DLQMessageSummary from(DLQMessage dlqMessage) {
        DLQMessageSummary summary = new DLQMessageSummary();
        BeanUtils.copyProperties(dlqMessage, summary);
        return summary;
    }
}
