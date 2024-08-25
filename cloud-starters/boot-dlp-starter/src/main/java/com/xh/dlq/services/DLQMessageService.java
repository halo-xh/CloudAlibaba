package com.xh.dlq.services;

import com.xh.dlq.dto.CreateDLQMessageRequest;
import com.xh.dlq.models.DLQMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DLQMessageService {

    DLQMessage create(CreateDLQMessageRequest request);

    Page<DLQMessage> page( Pageable pageable);

    void redo(Long dlqMsgId);

    void markAsDeleted(Long dlqMsgId);

}
