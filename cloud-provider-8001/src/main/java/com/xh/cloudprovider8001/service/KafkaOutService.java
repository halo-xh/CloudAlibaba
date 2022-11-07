package com.xh.cloudprovider8001.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.binder.kafka.KafkaMessageChannelBinder;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author xiaohong
 * @version 1.0
 * @date 2020/12/30 17:45
 * @description
 */
@Service
public class KafkaOutService {
    @Autowired
    private Source source;

    public KafkaOutService(Source source) {
        this.source = source;
    }

    public void sayHello(String name) {
        System.out.println("name = " + name);
        source.output().send(MessageBuilder.withPayload(name).build());
    }


    /**
     *
     *  不用cloud-stream。事务可行。
     *
     *   kafka:
     *     bootstrap-servers: 127.0.0.1:9092
     *     producer:
     *       transaction-id-prefix: tx-1
     *       acks: -1
     *     consumer:
     *       enable-auto-commit: false
     *
     *     @Autowired
     *     private KafkaTemplate kafkaTemplate;
     *
     *     @Transactional(rollbackFor = Exception.class)
     *     public void testTransaction() {
     *         kafkaTemplate.send(new ProducerRecord("order-topic","1q".getBytes()));
     *         int a = 3/0;
     *         kafkaTemplate.send(new ProducerRecord("order-topic","2q".getBytes()));
     *     }
     *
     *
     *
     * 1。首先  kafkaTransactionManager  是KafkaAutoConfiguration#kafkaTransactionManager(ProducerFactory) 装配，读的是spring.kakfa开头的配置
     *   所以，spring-cloud-stream的配置无效，容器中不存在kafkaTransactionManager
     * 2。即使手动加了kafkaTransactionManager 使用cloud-stream的output也无效，因为不是kafkaTransactionManager对应的producerFactory
     * 3。默认的 KafkaTemplate 用的也是spring.kakfa的配置的producerFactory
     * -- 那么是否可以尝试获取cloud-stream的producerFactory然后构建kafkaTransactionManager呢？
     *  查看源码发下kafka cloud-stream的KafkaMessageChannelBinder里其实都有 kafkaTransactionManager 但是没有注册到容器，那么是否可以手动注册，但是没有get方法。
     *  那试试反射？
     *
     *  尝试后无法获取KafkaMessageChannelBinder实例。
     *  最终原来官方都有说明：https://blog.csdn.net/u012549626/article/details/106957669
     *
     *
     */
    @Transactional(rollbackFor = Exception.class,transactionManager = "kafkaTransactionManager")
    public void testTransaction() {
        source.output().send(MessageBuilder.withPayload("2236").build());
        int a = 3/0;
        source.output().send(MessageBuilder.withPayload("2237").build());
    }

}
