package com.xh.audit.model;

import com.alibaba.fastjson.JSONObject;
import com.xh.audit.build.AbstractAuditLogModelMessageBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.aspectj.lang.JoinPoint;
import org.slf4j.event.Level;
import org.springframework.messaging.support.GenericMessage;

import java.util.Map;

import static com.xh.audit.build.BuildUtils.getValueByKey;

/**
 * @author xiaohong
 * @version 1.0
 * @date 2021/7/13 9:44
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StreamAuditLogModel extends BaseAuditLogModel {

    private String topic;

    private String msg;

    private String partition;

    private String group;

    public static class Builder extends AbstractAuditLogModelMessageBuilder{

        private Level level;

        private Long spendTime;

        private String serviceName;

        private String errorStack;

        public Builder(JoinPoint joinPoint, BaseAuditLogModel auditLog) {
            super(joinPoint, auditLog);
        }

        @Override
        public String buildParams(Map<String, Object> args) {
            JSONObject paramsJson = new JSONObject();
            for (String key : args.keySet()) {
                Object value = args.get(key);
                if (value != null && value.getClass().getName().contains("ConsumerRecord")) {
                    JSONObject kafkaMessage = new JSONObject();
                    kafkaMessage.put("topic", getValueByKey(value, "topic"));
                    kafkaMessage.put("value", getValueByKey(value, "value"));
                    paramsJson.put(key, kafkaMessage);
                } else if (value instanceof GenericMessage) {
                    GenericMessage messObject = (GenericMessage) value;
                    paramsJson.put(key, messObject.getPayload());
                }
            }
            return paramsJson.toJSONString();
        }

        @Override
        protected void buildExtraDetails(BaseAuditLogModel auditLog) {
            StreamAuditLogModel httpAuditLogModel = (StreamAuditLogModel) auditLog;
            httpAuditLogModel.setLevel(this.level);
            httpAuditLogModel.setSpendTime(this.spendTime);
            httpAuditLogModel.setServiceName(this.serviceName);
            httpAuditLogModel.setExceptionDetail(this.errorStack);
        }

        public StreamAuditLogModel.Builder info() {
            this.level = Level.INFO;
            return this;
        }

        public StreamAuditLogModel.Builder error() {
            this.level = Level.ERROR;
            return this;
        }

        public StreamAuditLogModel.Builder serviceName(String service) {
            this.serviceName = service;
            return this;
        }

        public StreamAuditLogModel.Builder spentTime(Long spentTime) {
            this.spendTime = spentTime;
            return this;
        }

        public StreamAuditLogModel.Builder errorStack(String errorStack) {
            this.errorStack = errorStack;
            return this;
        }

        public static StreamAuditLogModel.Builder getBuilder(JoinPoint joinPoint) {
            StreamAuditLogModel streamAuditLogModel = new StreamAuditLogModel();
            return new StreamAuditLogModel.Builder(joinPoint, streamAuditLogModel);
        }
    }

}
