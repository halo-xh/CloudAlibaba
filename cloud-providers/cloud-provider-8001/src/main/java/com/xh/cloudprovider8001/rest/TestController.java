package com.xh.cloudprovider8001.rest;

import com.xh.cloudprovider8001.limit.RedisRateChecker;
import com.xh.cloudprovider8001.service.KafkaOutService;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author  Xiao Hong
 * date  2020/12/19 19:13
 * description
 */

@Slf4j
@RestController
public class TestController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    KafkaOutService kafkaOutService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisRateChecker redisRateChecker;

    @RequestMapping("/nacos/{id}")
    public String getServerPort(@PathVariable("id") String id) {
        String traceId = TraceContext.traceId();
        RRateLimiter rateLimiter = redissonClient.getRateLimiter("getServerPort");
        boolean rate = rateLimiter.trySetRate(RateType.OVERALL, 10, 2, RateIntervalUnit.SECONDS);
        rateLimiter.acquire(1);
        log.info("acquired");
        kafkaOutService.sayHello("nacos registry , server port : " + serverPort + "\t id: " + id + "\t traceId:" + traceId);
        return "nacos registry , server port : " + serverPort + "\t id: " + id + "\t traceId:" + traceId;
    }

    @RequestMapping("/nacos2/{id}")
    public String getServerPort2(@PathVariable("id") String id) {
        boolean nacos2 = redisRateChecker.check("nacos2", 2000, 10L);
        while (!nacos2){
            nacos2 = redisRateChecker.check("nacos2", 2000, 10L);
        }
        return "nacos2";
    }

}
