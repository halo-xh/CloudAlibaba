package com.xh.cloudconfig7001.service;

import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @author xiaohong
 * @version 1.0
 * @date 2020/12/30 17:50
 * @description
 */
@Service
public class KafkaInService {

    private final Sink source;

    public KafkaInService(Sink source) {
        this.source = source;
    }

    public void sayHello(String name) {
        source.input().subscribe(System.out::println);
    }
}
