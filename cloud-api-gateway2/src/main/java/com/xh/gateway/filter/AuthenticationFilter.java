package com.xh.gateway.filter;

import com.xh.gateway.common.GatewayConstants;
import com.xh.gateway.config.SecurityProperties;
import com.xh.gateway.security.TokenProvider;
import com.xh.gateway.security.model.AnonymousUser;
import com.xh.gateway.security.model.LoginUser;
import com.xh.gateway.security.model.PermissionModel;
import com.xh.gateway.security.model.TenantInfo;
import com.xh.gateway.util.HttpRequestUtil;
import com.xh.gateway.util.JacksonJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 权限校验
 *
 * @author Xiao Hong
 * @since 2023-02-06
 */
@Slf4j
@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private static final PathMatcher PATH_MATCHER = new AntPathMatcher();
    private static final AnonymousUser ANONYMOUS_USER = new AnonymousUser();


    private final StringRedisTemplate redisTemplate;

    private final SecurityProperties properties;

    private final TokenProvider tokenProvider;


    public AuthenticationFilter(StringRedisTemplate redisTemplate, SecurityProperties properties, TokenProvider tokenProvider) {
        this.redisTemplate = redisTemplate;
        this.properties = properties;
        this.tokenProvider = tokenProvider;
    }

    @Trace
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 移除可能来自前端的 XT_USER_ID
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers((httpHeaders) -> httpHeaders.remove(GatewayConstants.XT_USER_ID)).build();
        // 认证
        Principal principal = ANONYMOUS_USER;
        try {
            String resolvedToken = resolveToken(exchange.getRequest());
            String tenantId = resolvedTenantId(exchange.getRequest());
            log.info("AuthenticationHandleFilter  tenantId:{} token: {}", tenantId, resolvedToken);
            if (StringUtils.hasText(resolvedToken)) {
                // 判断是否后台踢下线
                String md5DigestAsHex = DigestUtils.md5DigestAsHex(resolvedToken.getBytes());
                Boolean notKicked = redisTemplate.hasKey(md5DigestAsHex);
                if (notKicked != null && notKicked) {
                    // 判断token有效
                    boolean valid = this.tokenProvider.validateToken(tenantId, resolvedToken);
                    log.info("AuthenticationHandleFilter.tokenProvider.valid: {}, ", valid);
                    if (valid) {
                        LoginUser loginUser = this.tokenProvider.getAuthentication(resolvedToken, tenantId);
                        if (loginUser != null) {
                            serverHttpRequest = exchange.getRequest().mutate().headers((httpHeaders) -> httpHeaders.set(GatewayConstants.XT_USER_ID, loginUser.getId().toString())).build();
                            principal = loginUser;
                            log.info("AuthenticationHandleFilter LoginUserId:{} : LoginUserName:{}", loginUser.getId(), loginUser.getUsername());
                        }
                    } else {
                        log.info("AuthenticationHandleFilter invalid token");
                    }
                } else {
                    log.info("AuthenticationHandleFilter user has been kicked off.");
                }
            } else {
                log.info("AuthenticationHandleFilter ANONYMOUS User");
            }
        } catch (Exception e) {
            log.info("AuthenticationHandleFilter auth with exception set ANONYMOUS User", e);
        }
        // 鉴权
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        boolean hasPermission = this.authorize(exchange.getRequest(), route, principal);
        if (hasPermission) {
            return chain.filter(exchange.mutate().request(serverHttpRequest).build());
        } else {
            ServerHttpResponse httpResponse = exchange.getResponse();
            if (!httpResponse.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE)) {
                httpResponse.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            }
            if (principal instanceof AnonymousUser) {
                httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
                DataBuffer buffer = httpResponse.bufferFactory().wrap(GatewayConstants.UNAUTHORIZED_RESULT_JSON_BYTES);
                return httpResponse.writeWith(Mono.just(buffer));
            }
            httpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            DataBuffer buffer = httpResponse.bufferFactory().wrap(GatewayConstants.FORBIDDEN_RESULT_JSON_BYTES);
            return httpResponse.writeWith(Mono.just(buffer));
        }
    }

    private String resolvedTenantId(ServerHttpRequest request) {
        String tenantCode = HttpRequestUtil.getHeader(request, GatewayConstants.X_TENANT_CODE);
        if (!StringUtils.hasText(tenantCode)) {
            tenantCode = GatewayConstants.DEFAULT_TENANT;
        }
        String t = redisTemplate.opsForValue().get(GatewayConstants.TENANT_PREFIX + tenantCode);
        if (StringUtils.hasText(t)) {
            TenantInfo tenantInfo = JacksonJsonUtil.parseToObject(t, TenantInfo.class);
            return tenantInfo == null ? null : tenantInfo.getId();
        }
        return null;
    }

    private String resolveToken(ServerHttpRequest request) {
        String bearerToken = HttpRequestUtil.getHeader(request, HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(bearerToken)) {
            bearerToken = HttpRequestUtil.getCookie(request, "authorization");
        }
        String tokenStartWith = this.properties.getTokenStartWith();
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenStartWith)) {
            return bearerToken.substring(tokenStartWith.length());
        }
        return null;
    }

    public boolean authorize(ServerHttpRequest request, Route route, Principal principal) {
        try {
            PermissionModel permissionModel = getPermissionModel(request, route);
            if (permissionModel == null) {
                // 未从配置中获取到匹配的接口权限
                log.warn("AuthenticationHandleFilter permissionCheck: 未从配置中获取到匹配的接口权限:{}", request.getURI().getPath());
                return false;
            }
            List<String> roleCodes = permissionModel.getRoleCodes();
            // 直接检查接口是否匿名
            if (!CollectionUtils.isEmpty(roleCodes) && roleCodes.contains(GatewayConstants.ROLE_ANONYMOUS)) {
                return true;
            }
            // 检查非匿名接口且非匿名用户
            if (!(principal instanceof AnonymousUser)) {
                LoginUser loginUser = (LoginUser) principal;
                Set<String> allRoles = loginUser.getAllRoles();
                if (CollectionUtils.isEmpty(allRoles)) {
                    log.warn("AuthenticationHandleFilter permissionCheck: user[ id={}, userName={} ] has empty ROLE, permission check faild.", loginUser.getId(), loginUser.getUsername());
                    return false;
                }
                if (allRoles.contains(GatewayConstants.ROLE_ADMIN)) {
                    log.info("AuthenticationHandleFilter permissionCheck: user[ id={}, userName={} ] has [ROME_ADMIN], permission check success.", loginUser.getId(), loginUser.getUsername());
                    return true;
                } else {
                    for (String roleCode : roleCodes) {
                        if (allRoles.contains(roleCode)) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception exception) {
            log.error("AuthenticationHandleFilter permissionCheck: PermissionCheckError", exception);
        }
        return false;
    }

    private PermissionModel getPermissionModel(ServerHttpRequest request, Route route) {
        List<String> modelList = redisTemplate.opsForList().range(GatewayConstants.PERMISSION_KEY + route.getId(), 0L, -1L);
        if (!CollectionUtils.isEmpty(modelList)) {
            String path = request.getURI().getPath();
            String newPath = "/" + Arrays.stream(StringUtils.tokenizeToStringArray(path, "/"))
                    .skip(1).collect(Collectors.joining("/"));
            // todo. 性能优化
            List<PermissionModel> permissionModels = new ArrayList<>();
            for (String model : modelList) {
                PermissionModel permissionModel = JacksonJsonUtil.parseToObject(model, PermissionModel.class);
                if (permissionModel != null) {
                    String httpMethod = permissionModel.getHttpMethod();
                    String resourceUrl = permissionModel.getResourceUrl();
                    if (PATH_MATCHER.match(resourceUrl, newPath)
                            && request.getMethod() != null && request.getMethod().matches(httpMethod)) {
                        permissionModels.add(permissionModel);
                    }
                }
            }
            // 兼容一个接口多条配置数据
            if (!CollectionUtils.isEmpty(permissionModels)) {
                PermissionModel permissionModel = permissionModels.get(0);
                for (int i = 1; i < permissionModels.size(); i++) {
                    PermissionModel model = permissionModels.get(i);
                    List<String> roleCodes = model.getRoleCodes();
                    if (!CollectionUtils.isEmpty(roleCodes)) {
                        permissionModel.getRoleCodes().addAll(roleCodes);
                    }
                }
                return permissionModel;
            }
        } else {
            log.error("未查询到REDIS接口权限配置");
        }
        return null;
    }


    @Override
    public int getOrder() {
        return 200;
    }


}
