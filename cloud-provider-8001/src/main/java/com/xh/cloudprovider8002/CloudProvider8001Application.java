package com.xh.cloudprovider8002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudProvider8001Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudProvider8001Application.class, args);
    }

}
