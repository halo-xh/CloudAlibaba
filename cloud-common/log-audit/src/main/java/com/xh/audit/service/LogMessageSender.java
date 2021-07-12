package com.xh.audit.service;


import com.xh.audit.model.AuditLogModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 * @author xiaohong
 */
public interface LogMessageSender {

    @Async
    void send(AuditLogModel auditLog) throws Throwable;

}
