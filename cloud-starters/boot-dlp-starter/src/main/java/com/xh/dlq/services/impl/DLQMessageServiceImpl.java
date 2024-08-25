package com.xh.dlq.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xh.dlq.dto.CreateDLQMessageRequest;
import com.xh.dlq.models.DLQMessage;
import com.xh.dlq.repositories.DLQMessageRepository;
import com.xh.dlq.services.DLQMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class DLQMessageServiceImpl implements DLQMessageService {

    @Autowired
    private DLQMessageRepository dlqMessageRepository;

    @Autowired
    private BinderAwareChannelResolver resolver;

    @Override
    public void redo(Long dlqMsgId) {
        DLQMessage dlqMessage = dlqMessageRepository.findById(dlqMsgId).get();
        Assert.isTrue(!dlqMessage.getHandled(), "已处理过的死信不能再次处理。");
        dlqMessage.setHandled(true);
        dlqMessage.setHandleAt(new Date());
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> headers = new HashMap<>();
            resolver.resolveDestination(dlqMessage.getOriginalTopic())
                    .send(new GenericMessage<>(mapper.readValue(dlqMessage.getPayload(), Object.class), headers));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void markAsDeleted(Long dlqMsgId) {
        DLQMessage dlqMessage = dlqMessageRepository.findById(dlqMsgId).get();
        dlqMessage.setDeleted(Boolean.TRUE);
        dlqMessageRepository.save(dlqMessage);
    }

    @Override
    public DLQMessage create(CreateDLQMessageRequest request) {
        DLQMessage dlqMessage = new DLQMessage();
        dlqMessage.setOriginalTopic(request.getOriginalTopic());
        dlqMessage.setBusinessEvent(request.getBusinessEvent());
        dlqMessage.setPayload(request.getPayload());
        dlqMessage.setExceptionMessage(request.getExceptionMessage());
        dlqMessage.setExceptionStacktrace(request.getExceptionStacktrace());
        return dlqMessageRepository.save(dlqMessage);
    }

    @Override
    public Page<DLQMessage> page(Pageable pageable) {
        return dlqMessageRepository.findAll(pageable);
    }
}
