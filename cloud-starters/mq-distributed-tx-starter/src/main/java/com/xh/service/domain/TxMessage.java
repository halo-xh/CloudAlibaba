package com.xh.service.domain;

// 事务消息
public interface TxMessage {

    String businessModule();

    String businessKey();

    String content();
}
