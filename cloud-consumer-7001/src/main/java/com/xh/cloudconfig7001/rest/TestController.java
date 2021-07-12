package com.xh.cloudconfig7001.rest;

import com.xh.cloudconfig7001.feign.UserService;
import com.xh.common.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;

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

    @Resource
    private UserService userService;

    @Value("${service-url.service-provider}")
    private String serverURL;

    @RequestMapping("/consumer/nacos/{id}")
    public String getServerPort(@PathVariable("id") String id) {
         return restTemplate.getForObject(serverURL + "/provider/nacos/" + id, String.class);
    }

    @RequestMapping("/consumer/nacos/user")
    public String getServerPort(){
        User user = new User();
        user.setId(1767446417L);
        user.setBirth(new Date());
        return userService.getUser(user);
    }

}
