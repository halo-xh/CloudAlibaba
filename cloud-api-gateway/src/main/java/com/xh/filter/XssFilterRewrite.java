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
import org.springframework.core.io.buffer.NettyDataBuffer;
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

@Slf4j
@Component
@Data
public class XssFilterRewrite implements GlobalFilter, Ordered {

    private List<String> urls = new ArrayList<>();
    private static final PathMatcher PATH_MATCHER = new AntPathMatcher();

    public static final int ORDER = 300;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        //白名单url直接放行
        for (String url : urls) {
            if (PATH_MATCHER.match(url, uri.getPath())) {
                log.info("XssFilter 白名单接口:{}", uri.getPath());
                return chain.filter(exchange);
            }
        }

        String method = request.getMethodValue();
        String contentType = request.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
        boolean postFlag = (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equalsIgnoreCase(contentType)
                || MediaType.APPLICATION_JSON_VALUE.equals(contentType));
        if (!postFlag) {
            log.info("XssFilter 参数类型放行接口:{}", uri.getPath());
            return chain.filter(exchange);
        }
        if ((HttpMethod.POST.name().equals(method) || HttpMethod.PUT.name().equals(method))) {
            ServerHttpRequest newRequest = new ServerHttpRequestDecorator(request) {
                @Override
                public Flux<DataBuffer> getBody() {
                    Flux<DataBuffer> body = super.getBody();
                    return body.map(dataBuffer -> {
                        if (dataBuffer != null) {
                            byte[] oldBytes = new byte[dataBuffer.readableByteCount()];
                            dataBuffer.read(oldBytes);
                            DataBufferUtils.release(dataBuffer);
                            String bodyString = new String(oldBytes, StandardCharsets.UTF_8);
                            log.debug("[{}：{}] XSS处理前参数：{}", method, uri.getPath(), bodyString);
                            String xssClean =  xssClean(bodyString);
                            log.info("[{}：{}] XSS处理后参数：{}", method, uri.getPath(), bodyString);
                            // 重新构造body
                            byte[] newBytes = xssClean.getBytes(StandardCharsets.UTF_8);
                            return toDataBuffer(newBytes);
                        }
                        return null;
                    });
                }

                @Override
                public HttpHeaders getHeaders() {
                    // 重新构造header
                    HttpHeaders headers = new HttpHeaders();
                    headers.putAll(super.getHeaders());
                    // 由于修改了传递参数，需要重新设置CONTENT_LENGTH，长度是字节长度，不是字符串长度
                    headers.remove(HttpHeaders.CONTENT_LENGTH);
                    headers.set(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf8");
                    return headers;
                }
            };
            log.info("XssFilter POST请求接口:{}", uri.getPath());
            return chain.filter(exchange.mutate().request(newRequest).build());
        } else if (HttpMethod.GET.name().equals(method)) {

            String rawQuery = uri.getRawQuery();
            if (!StringUtils.hasText(rawQuery)) {
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

    private String xssClean(String bodyString) {
        return bodyString;
    }


    @Override
    public int getOrder() {
        return ORDER;
    }
}
