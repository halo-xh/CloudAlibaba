package com.xh.zz.service;

import com.xh.zz.entity.TransactionalMessage;
import com.xh.zz.service.domain.Destination;
import com.xh.zz.service.domain.ExchangeType;
import com.xh.zz.service.domain.TxMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Xiao Hong
 * @since 2022-11-09
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitTransactionalMessageService implements TransactionalMessageService {

    private final AmqpAdmin amqpAdmin;
    private final TransactionalMessageManagementService managementService;

    private static final ConcurrentMap<String, Boolean> QUEUE_ALREADY_DECLARE = new ConcurrentHashMap<>();

    @Override
    public void sendTransactionalMessage(Destination destination, TxMessage message) {
        String queueName = destination.queueName();
        String exchangeName = destination.exchangeName();
        String routingKey = destination.routingKey();
        ExchangeType exchangeType = destination.exchangeType();
        // 原子性的预声明
        QUEUE_ALREADY_DECLARE.computeIfAbsent(queueName, k -> {
            Queue queue = new Queue(queueName);
            amqpAdmin.declareQueue(queue);
            Exchange exchange = new CustomExchange(exchangeName, exchangeType.getType());
            amqpAdmin.declareExchange(exchange);
            Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
            amqpAdmin.declareBinding(binding);
            return true;
        });
        TransactionalMessage record = new TransactionalMessage();
        record.setQueueName(queueName);
        record.setExchangeName(exchangeName);
        record.setExchangeType(exchangeType.getType());
        record.setRoutingKey(routingKey);
        record.setBusinessModule(message.businessModule());
        record.setBusinessKey(message.businessKey());
        String content = message.content();
        // 保存事务消息记录
        managementService.saveTransactionalMessageRecord(record, content);
        // 注册事务同步器
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                managementService.sendMessageSync(record, content);
            }
        });
    }
}

