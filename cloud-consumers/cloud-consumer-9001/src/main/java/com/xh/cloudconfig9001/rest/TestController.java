package com.xh.cloudconfig9001.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
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
@RefreshScope
public class TestController {

    @Value("${server.port}")
    private String config;

    @RequestMapping("/config/info")
    public String getServerPort() {
        log.info("info");
         return config;
    }


}
