package com.xh.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Configuration
public class GatewaySentinelConfiguration {

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    /**
     * 接口限流默认1秒允许通过100个
     */
    @Value("${xt.common.flow.count:100}")
    private Integer count;
    /**
     * 接口限流默认1秒统计一次
     */
    @Value("${xt.common.flow.intervalSec:1}")
    private Integer intervalSec;

    /**
     * 微服务API分组名称
     */
    private static String APIGROUPNAMESPACE = "xt-saas-admin-service";

    public GatewaySentinelConfiguration(ObjectProvider<List<ViewResolver>> vewResolverProvider, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = vewResolverProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /**
     * 初始化限流过滤器
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    /**
     * 配置初始化的限流参数
     */
    @PostConstruct
    public void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        // 以路由id为限流维度，resource为路由id，count为阈值，interval为统计时间窗口，默认1秒
        // 以api分组为限流维度
        rules.add(new GatewayFlowRule(APIGROUPNAMESPACE).setCount(count).setIntervalSec(intervalSec));
        GatewayRuleManager.loadRules(rules);
    }

    /**
     * 配置异常处理器
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    /**
     * 自定义限流异常返回页面
     */
    @PostConstruct
    public void initBlockHandlers() {
        BlockRequestHandler blockRequestHandler = (serverWebExchange, throwable) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("code", 429);
            map.put("message", "当前访问人数过多,请稍后重试");
            return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                    .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                    .body(BodyInserters.fromValue(map));
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }

    /**
     * api维度限流，自定义api分组
     */
    @PostConstruct
    public void initCustomizedApis() {
        Set<ApiDefinition> definitions = new HashSet<>();
        // api分组，apiName自定义唯一即可
        ApiDefinition api = new ApiDefinition(APIGROUPNAMESPACE)//Api 分组名组名字
                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                    add(new ApiPathPredicateItem().setPattern("**/adminservice/**"));
                }});
        definitions.add(api);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }


    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(2))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new FastJsonRedisSerializer<>(Object.class)));
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(configuration)
                .transactionAware()
                .build();
    }

}
