package com.xh.cloudconfig7001;

import com.xh.audit.AuditApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@Import({AuditApplication.class})
@EnableBinding({Processor.class})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CloudConsumer9002Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumer9002Application.class, args);
    }

}
