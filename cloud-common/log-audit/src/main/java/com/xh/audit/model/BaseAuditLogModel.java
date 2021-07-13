package com.xh.audit.model;

import lombok.Data;
import org.slf4j.event.Level;


/**
 * @author xiaohong
 * @version 1.0
 * @date 2021/7/12 16:30
 * @description
 */
@Data
public class BaseAuditLogModel {

    private Level level;

    private Long spendTime;

    private String method;

    private String params;

    private String serviceName;

    private Long createTime;

    private String description;

    private String exceptionDetail;

}
