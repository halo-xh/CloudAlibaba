package com.xh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudApiGatewayApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(CloudApiGatewayApplication.class, args);
		String auto = applicationContext.getEnvironment().getProperty("spring.cloud.gateway");
		System.err.println("spring.cloud.gateway:" + auto);
	}

}
