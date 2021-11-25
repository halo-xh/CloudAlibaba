package com.xh.cloudconfig7001.rest;

import com.xh.cloudconfig7001.feign.UserService;
import com.xh.common.User;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * author  Xiao Hong
 * date  2020/12/19 19:13
 * description
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private Source out;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private UserService userService;

    @RequestMapping("/consumer/nacos/user")
    public String getServerPort() {
        User user = new User();
        user.setId(1767446417L);
        user.setBirth(new Date());
        return userService.getUser(user);
    }

    @StreamListener(value = Sink.INPUT, condition = "headers['name'] =='test1'")
    public void listener(@Payload String order) {
        log.info("receive msg by StreamListener test1 : {}", order);
    }

    @StreamListener(value = Sink.INPUT, condition = "headers['name']=='test1'")
    public void listener1(@Payload String order) {
        log.info("receive msg by StreamListener test1 : {}", order);
    }

    @StreamListener(value = Sink.INPUT, condition = "headers['name']=='test2'")
    public void listener2(@Payload String order) {
        log.info("receive msg by StreamListener test2 : {}", order);
    }

//    @Scheduled(cron = "*/1 * * * * ?")
    public void sendOrder() {
        Map<String, Object> header = new HashMap<>();
        header.put("name", "test1");
        boolean send = out.output().send(new GenericMessage<>(new Date().toString(), header));
        log.info("test1 send :{}", send);
    }

//    @Scheduled(cron = "*/1 * * * * ?")
    public void sendOrder2() {
        Map<String, Object> header = new HashMap<>();
        header.put("name", "test2");
        boolean send = out.output().send(new GenericMessage<>(new Date().toString(), header));
        log.info("test2 send :{}", send);
    }

}
