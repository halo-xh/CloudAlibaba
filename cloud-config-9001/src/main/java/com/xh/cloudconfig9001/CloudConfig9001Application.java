package com.xh.cloudconfig9001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudConfig9001Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CloudConfig9001Application.class, args);
        String userName = applicationContext.getEnvironment().getProperty("spring.jpa.show-sql");
        String datasource = applicationContext.getEnvironment().getProperty("spring.datasource.name");
        System.err.println("spring.jpa.show-sql :" + userName);
        System.err.println("spring.datasource:" + datasource);
    }

}
