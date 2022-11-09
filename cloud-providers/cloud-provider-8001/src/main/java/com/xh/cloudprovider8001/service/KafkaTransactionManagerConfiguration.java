package com.xh.cloudprovider8001.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.BinderFactory;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.cloud.stream.binder.kafka.KafkaMessageChannelBinder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.messaging.MessageChannel;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Field;

/**
 * @author Xiao Hong
 * @since 2022-05-12
 */
@Configuration
public class KafkaTransactionManagerConfiguration {


    @Bean("kafkaTransactionManager")
    public PlatformTransactionManager kafkaTransactionManager(BinderFactory binderFactory)  {
        ProducerFactory<byte[], byte[]> transactionalProducerFactory = ((KafkaMessageChannelBinder) binderFactory.getBinder(null, MessageChannel.class)).getTransactionalProducerFactory();
        return new KafkaTransactionManager<>(transactionalProducerFactory);
    }



}
