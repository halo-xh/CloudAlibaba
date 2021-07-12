package com.xh.audit.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * author  Xiao Hong
 * date  2021/7/12 19:56
 * description
 */

public interface AuditLogProcessor {

    /**
     * Name of the input channel.
     */
    String INPUT = "input-audit";

    /**
     * @return input channel
     */
    @Output(AuditLogProcessor.INPUT)
    MessageChannel in();

    /**
     * Name of the output channel.
     */
    String OUTPUT = "output-audit";

    /**
     * @return output channel
     */
    @Output(AuditLogProcessor.OUTPUT)
    MessageChannel out();

}
