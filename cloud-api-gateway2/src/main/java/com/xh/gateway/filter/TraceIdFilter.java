package com.xh.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 实际上如果使用 TraceContext 获取 trace id 无需手动传递. 此处放置可能是为了方便外部系统记录跟踪.
 *
 * @author Xiao Hong
 * @since 2023-02-06
 */
@Slf4j
@Component
public class TraceIdFilter implements GlobalFilter, Ordered {

    public TraceIdFilter() {
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String traceId = TraceContext.traceId();
        log.info("uri:{}, traceId:{}", exchange.getRequest().getURI(), traceId);
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers((httpHeaders) -> {
            httpHeaders.add("x-traceId-header", traceId);
        }).build();
        return chain.filter(exchange.mutate().request(serverHttpRequest).build());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
