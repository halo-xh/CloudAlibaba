package com.xh.dlq.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateDLQMessageRequest {

    private String originalTopic;

    private String businessEvent;

    private String payload;

    private String exceptionMessage;

    private String exceptionStacktrace;
}
