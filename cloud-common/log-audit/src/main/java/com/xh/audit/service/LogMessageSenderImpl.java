package com.xh.audit.service;

import com.xh.audit.model.BaseAuditLogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

@Service
public class LogMessageSenderImpl implements LogMessageSender {

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    private AuditLogProcessor auditLogProcessor;

    @Override
    public void send(BaseAuditLogModel auditLog) throws Throwable {
        auditLogProcessor.out().send(new GenericMessage<>(auditLog));
    }
}
