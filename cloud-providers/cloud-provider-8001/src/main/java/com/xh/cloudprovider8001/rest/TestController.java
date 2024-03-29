package com.xh.cloudprovider8001.rest;

import com.xh.cloudprovider8001.service.KafkaOutService;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
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

@RestController
public class TestController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    KafkaOutService kafkaOutService;

    @RequestMapping("/nacos/{id}")
    public String getServerPort(@PathVariable("id") String id) {
        String traceId = TraceContext.traceId();
        kafkaOutService.sayHello("nacos registry , server port : " + serverPort + "\t id: " + id + "\t traceId:" + traceId);
        return "nacos registry , server port : " + serverPort + "\t id: " + id + "\t traceId:" + traceId;
    }

}
