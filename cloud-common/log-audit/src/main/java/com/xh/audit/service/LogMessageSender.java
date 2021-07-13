package com.xh.audit.service;


import com.xh.audit.model.BaseAuditLogModel;
import org.springframework.scheduling.annotation.Async;

/**
 * @author xiaohong
 */
public interface LogMessageSender {

    @Async
    void send(BaseAuditLogModel auditLog) throws Throwable;

}
