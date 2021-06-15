package com.xh.cloudprovider8002.rest;

import com.xh.common.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/provider/nacos/{id}")
    public String getServerPort(@PathVariable("id") String id) {
        return "nacos registry , server port : " + serverPort + "\t id: " + id;
    }

    @PostMapping("/provider/nacos/user")
    public String getUser(@RequestBody User user) {
        log.info(user.toString());
        user.setId(user.getId() +123L);
        log.info(user.toString());
        return user.toString();
    }

}
