package com.xh.gateway;

import com.alibaba.cloud.nacos.endpoint.NacosDiscoveryEndpointAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        NacosDiscoveryEndpointAutoConfiguration.class,
        org.springframework.cloud.client.serviceregistry.ServiceRegistryAutoConfiguration.class,
        org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationAutoConfiguration.class,
        ReactiveUserDetailsServiceAutoConfiguration.class
})
@EnableCaching
@EnableFeignClients
@RefreshScope
@EnableConfigurationProperties
public class XtGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(XtGatewayApplication.class, args);
    }

}
