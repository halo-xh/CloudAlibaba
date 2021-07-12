package com.xh.audit.model;

import lombok.Data;
import org.slf4j.event.Level;

import java.time.LocalDateTime;

/**
 * @author xiaohong
 * @version 1.0
 * @date 2021/7/12 16:30
 * @description
 */
@Data
public class AuditLogModel {

    private Long createTime;

    private LocalDateTime createdAt;

    private Long uid;

    private String userName;

    private String description;

    private String browser;

    private String innerIPAddress;

    private String outerIPAddress;

    private String exceptionDetail;

    private long spendTime;

    private String method;

    private String params;

    private String serviceName;

    private String roles;

    private String address;

    private String mobile;

    private String referer;

    private Level level;

}
