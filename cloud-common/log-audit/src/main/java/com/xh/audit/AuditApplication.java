package com.xh.audit;

import com.xh.audit.service.AuditLogProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(AuditLogProcessor.class)
@EnableAutoConfiguration
public class AuditApplication {
}
