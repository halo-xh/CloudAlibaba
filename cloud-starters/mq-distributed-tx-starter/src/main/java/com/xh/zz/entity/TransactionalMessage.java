package com.xh.zz.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Xiao Hong
 * @since 2022-11-09
 */
@Data
public class TransactionalMessage {

    private Long id;
    private LocalDateTime createTime;
    private LocalDateTime editTime;
    private String creator;
    private String editor;
    private Integer deleted;
    private Integer currentRetryTimes;
    private Integer maxRetryTimes;
    private String queueName;
    private String exchangeName;
    private String exchangeType;
    private String routingKey;
    private String businessModule;
    private String businessKey;
    private LocalDateTime nextScheduleTime;
    private Integer messageStatus;
    private Long initBackoff;
    private Integer backoffFactor;
}

