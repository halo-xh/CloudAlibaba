package com.xh.service;

import com.xh.service.domain.Destination;
import com.xh.service.domain.TxMessage;

/**
 * @author Xiao Hong
 * @since 2022-11-09
 */
// 对外提供的服务类接口
public interface TransactionalMessageService {

    void sendTransactionalMessage(Destination destination, TxMessage message);
}


