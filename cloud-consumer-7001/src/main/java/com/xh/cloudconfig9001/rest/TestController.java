package com.xh.cloudconfig9001.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * author  Xiao Hong
 * date  2020/12/19 19:13
 * description
 */

@RestController
public class TestController {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.service-provider}")
    private String serverURL;

    @RequestMapping("/consumer/nacos/{id}")
    public String getServerPort(@PathVariable("id") String id) {
         return restTemplate.getForObject(serverURL + "/provider/nacos/" + id, String.class);
    }


}
