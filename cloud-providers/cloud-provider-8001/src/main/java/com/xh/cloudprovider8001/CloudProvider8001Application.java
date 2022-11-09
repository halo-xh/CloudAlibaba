package com.xh.cloudprovider8001;

import com.xh.audit.AuditApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;


@Import({AuditApplication.class})
@EnableBinding({Processor.class})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CloudProvider8001Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CloudProvider8001Application.class, args);
        String userName = applicationContext.getEnvironment().getProperty("spring.jpa.show-sql");
        String datasource = applicationContext.getEnvironment().getProperty("spring.datasource.name");
        System.err.println("spring.jpa.show-sql :" + userName);
        System.err.println("spring.datasource:" + datasource);
    }

}
