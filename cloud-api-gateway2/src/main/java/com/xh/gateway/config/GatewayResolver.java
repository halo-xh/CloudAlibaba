package com.xh.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * 限流配置bean（url）
 */
@Configuration
public class GatewayResolver {

    @Bean("urlKeyResolver")
    public KeyResolver urlKeyResolver() {
        return exchange -> {
            String url = exchange.getRequest().getPath().toString();
            return Mono.just(url);
        };
    }
}
