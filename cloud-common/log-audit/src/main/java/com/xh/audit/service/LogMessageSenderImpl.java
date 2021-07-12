package com.xh.audit.service;

import com.xh.audit.model.AuditLogModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

/**
 * create by menglw at 2020/7/21 21:41
 *
 * @author menglw
 */
@Service
public class LogMessageSenderImpl implements LogMessageSender {

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    private AuditLogProcessor auditLogProcessor;

    @Override
    public void send(AuditLogModel auditLog) throws Throwable {
        auditLogProcessor.out().send(new GenericMessage<>(auditLog));
    }
}
