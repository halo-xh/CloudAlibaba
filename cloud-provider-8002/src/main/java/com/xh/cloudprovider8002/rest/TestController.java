package com.xh.cloudprovider8002.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author  Xiao Hong
 * date  2020/12/19 19:13
 * description
 */

@RestController
public class TestController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/provider/nacos/{id}")
    public String getServerPort(@PathVariable("id") String id) {
        return "nacos registry , server port : " + serverPort + "\t id: " + id;
    }

}
