package com.xh.zz.service;

import com.xh.zz.service.domain.Destination;
import com.xh.zz.service.domain.TxMessage;

/**
 * @author Xiao Hong
 * @since 2022-11-09
 */
// 对外提供的服务类接口
public interface TransactionalMessageService {

    void sendTransactionalMessage(Destination destination, TxMessage message);
}


