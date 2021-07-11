package com.xh.cloudconfig7001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding({Processor.class})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CloudConsumer7001Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumer7001Application.class, args);
    }

}
