package com.xh.gateway.filter;

import com.xh.gateway.common.GatewayConstants;
import com.xh.gateway.util.HttpRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * 用户权限刷新后 刷新token。todo.
 *
 * @author Xiao Hong
 * @since 2023-02-08
 */
@Slf4j
//@Component
public class RefreshTokenFilter implements GlobalFilter, Ordered {


    private final StringRedisTemplate stringRedisTemplate;


    public RefreshTokenFilter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> rewriteHeaders(exchange)));
    }

    private void rewriteHeaders(ServerWebExchange exchange) {
        final HttpHeaders headers = exchange.getResponse().getHeaders();
        String userId = HttpRequestUtil.getHeader(exchange.getRequest(), GatewayConstants.XT_USER_ID);
        if (StringUtils.hasText(userId)) {
            String newToken = (String) stringRedisTemplate.opsForHash().get("userNeedRefreshToken", userId);
            if (StringUtils.hasText(newToken)) {
                headers.set(HttpHeaders.AUTHORIZATION, newToken);
            }
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
