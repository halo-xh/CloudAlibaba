package com.example.cloudsimple;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.example.id.IdCreatorConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;


@Slf4j
@EnableMethodCache(basePackages = "com.example")
@SpringBootApplication
@Import(IdCreatorConfiguration.class)
public class CloudSimpleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CloudSimpleApplication.class, args);

    }

}
