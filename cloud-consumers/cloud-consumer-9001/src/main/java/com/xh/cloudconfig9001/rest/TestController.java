package com.xh.cloudconfig9001.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


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
        String s = TraceContext.traceId();
        log.info("trace id :{}", s);
        return config;
    }


}
