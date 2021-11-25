package com.xh.cloudconfig9001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudConsumer9001Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CloudConsumer9001Application.class, args);
        String auto = applicationContext.getEnvironment().getProperty("spring.cloud.stream.kafka.binder.auto-create-topics");
        System.err.println("spring.cloud.stream.kafka.binder.auto-create-topics :" + auto);
    }

}
