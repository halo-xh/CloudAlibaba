package com.xh.cloudprovider8001.service;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author xiaohong
 * @version 1.0
 * @date 2020/12/30 17:45
 * @description
 */
@Service
public class KafkaOutService {
    private Source source;

    public KafkaOutService(Source source) {
        this.source = source;
    }

    public void sayHello(String name) {
        source.output().send(MessageBuilder.withPayload(name).build());
    }
}
