package com.xh.dlq.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xh.dlq.dto.CreateDLQMessageRequest;
import com.xh.dlq.services.DLQMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DLQListener {

    // ref: KafkaMessageChannelBinder
    static final String X_EXCEPTION_MESSAGE = "x-exception-message";

    static final String X_EXCEPTION_STACKTRACE = "x-exception-stacktrace";

    static final String X_ORIGINAL_TOPIC = "x-original-topic";

    private static final String FALLBACK_STRING = "UNKNOWN";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DLQMessageService dlqMessageService;

    @StreamListener(Sink.INPUT)
    public void errorMsg(Message<?> errorMessage) {
        CreateDLQMessageRequest request =
                CreateDLQMessageRequest.builder()
                        .payload(extractPayload(errorMessage.getPayload()))
                        .originalTopic(fallbackAwareString(errorMessage.getHeaders().get(X_ORIGINAL_TOPIC)))
                        .exceptionMessage(fallbackAwareString(errorMessage.getHeaders().get(X_EXCEPTION_MESSAGE)))
                        .exceptionStacktrace(fallbackAwareString(errorMessage.getHeaders().get(X_EXCEPTION_STACKTRACE)))
                        .build();
        dlqMessageService.create(request);
    }

    private <T> String extractPayload(T payload) {
        if (payload == null) {
            return FALLBACK_STRING;
        }
        if (payload instanceof byte[]) {
            return fallbackAwareString(payload);
        }
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Could serialize as JSON ", e);
        }
    }

    private String fallbackAwareString(Object value) {
        if (value == null) {
            return FALLBACK_STRING;
        }
        if (value instanceof byte[]) {
            return new String((byte[]) value);
        }
        return Objects.toString(value);
    }
}
