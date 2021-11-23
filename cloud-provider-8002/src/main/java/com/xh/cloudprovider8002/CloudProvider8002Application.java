package com.xh.cloudprovider8002;

import com.xh.audit.AuditApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@EnableBinding({ Processor.class})
@Import({AuditApplication.class})
@SpringBootApplication
@EnableDiscoveryClient
public class CloudProvider8002Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudProvider8002Application.class, args);
    }

}
