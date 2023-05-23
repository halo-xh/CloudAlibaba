package com.xh.filter;

import io.netty.buffer.ByteBufAllocator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Description 描述
 *
 * @author Nothing
 * @date 2023年04月26日 17:53
 */
@Slf4j
@Component
@Data
public class XssFilter implements GlobalFilter, Ordered {

    private List<String> urls = new ArrayList<>();
    private static final PathMatcher PATH_MATCHER = new AntPathMatcher();

    public static final int ORDER = 301;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        //白名单url直接放行
        for (int i = 0; i < urls.size(); i++) {
            if (PATH_MATCHER.match(urls.get(i), uri.getPath())) {
                log.info("XssFilter 白名单接口:{}", uri.getPath());
                return chain.filter(exchange);
            }
        }

        String method = request.getMethodValue();
        if ((HttpMethod.POST.name().equals(method) || HttpMethod.PUT.name().equals(method))) {
            return DataBufferUtils.join(request.getBody()).flatMap(d -> Mono.just(Optional.of(d))).defaultIfEmpty(Optional.empty())
                    .flatMap(optional -> {
                        // 取出body中的参数
                        String bodyString = "";
                        if (optional.isPresent()) {
                            DataBuffer dataBuffer = optional.get();
                            byte[] oldBytes = new byte[dataBuffer.readableByteCount()];
                            dataBuffer.read(oldBytes);
                            DataBufferUtils.release(dataBuffer);
                            bodyString = new String(oldBytes, StandardCharsets.UTF_8);
                        }
                        HttpHeaders httpHeaders = request.getHeaders();
                        // 执行XSS清理
                        log.debug("{} - [{}：{}] XSS处理前参数：{}", method, uri.getPath(), bodyString);
                        bodyString =  xssClean(bodyString);
                        log.info("{} - [{}：{}] 参数：{}", method, uri.getPath(), bodyString);

                        ServerHttpRequest newRequest = request.mutate().uri(uri).build();

                        // 重新构造body
                        byte[] newBytes = bodyString.getBytes(StandardCharsets.UTF_8);
                        DataBuffer bodyDataBuffer = toDataBuffer(newBytes);
                        Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);

                        // 重新构造header
                        HttpHeaders headers = new HttpHeaders();
                        headers.putAll(httpHeaders);
                        // 由于修改了传递参数，需要重新设置CONTENT_LENGTH，长度是字节长度，不是字符串长度
                        int length = newBytes.length;
                        headers.remove(HttpHeaders.CONTENT_LENGTH);
                        headers.setContentLength(length);
                        headers.set(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf8");
                        // 重写ServerHttpRequestDecorator，修改了body和header，重写getBody和getHeaders方法
                        newRequest = new ServerHttpRequestDecorator(newRequest) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                return bodyFlux;
                            }

                            @Override
                            public HttpHeaders getHeaders() {
                                return headers;
                            }
                        };
                        log.info("XssFilter POST请求接口:{}", uri.getPath());
                        return chain.filter(exchange.mutate().request(newRequest).build());
                    });
        } else if(HttpMethod.GET.name().equals(method)){

            String rawQuery = uri.getRawQuery();
            if (!StringUtils.hasText(rawQuery)){
                log.info("XssFilter GET请求参数为空接口:{}", uri.getPath());
                return chain.filter(exchange);
            }
            rawQuery =  xssClean(rawQuery);
            try {
                URI newUri = UriComponentsBuilder.fromUri(uri)
                        .replaceQuery(rawQuery)
                        .build(true)
                        .toUri();

                ServerHttpRequest newRequest = exchange.getRequest().mutate()
                        .uri(newUri).build();
                log.info("XssFilter GET请求接口:{},处理之后的参数:{}", uri.getPath(), rawQuery);
                return chain.filter(exchange.mutate().request(newRequest).build());
            } catch (Exception e) {
                log.error("get请求清理xss攻击异常", e);
                throw new IllegalStateException("Invalid URI query: \"" + rawQuery + "\"");
            }
        } else {
            return chain.filter(exchange);
        }
    }

    private String xssClean(String bodyString) {
        return bodyString;
    }

    /**
     * 字节数组转DataBuffer
     *
     * @param bytes 字节数组
     * @return DataBuffer
     */
    private DataBuffer toDataBuffer(byte[] bytes) {
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
