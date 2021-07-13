package com.xh.audit.model;

import com.alibaba.fastjson.JSONObject;
import com.xh.audit.build.AbstractAuditLogModelMessageBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.aspectj.lang.JoinPoint;
import org.slf4j.event.Level;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author xiaohong
 * @version 1.0
 * @date 2021/7/13 9:42
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HttpAuditLogModel extends BaseAuditLogModel {

    private Long uid;

    private String userName;

    public static class Builder extends AbstractAuditLogModelMessageBuilder {

        private Long uid;

        private String userName;

        private Level level;

        private Long spendTime;

        private String serviceName;

        private String errorStack;

        private BaseAuditLogModel baseAuditLogModel;

        private UserDetails principal;// todo. anthentication DO

        public Builder(JoinPoint joinPoint, BaseAuditLogModel auditLog) {
            super(joinPoint, auditLog);
        }

        @Override
        public String buildParams(Map<String, Object> args) {
            JSONObject paramsJson = new JSONObject();
            for (String key : args.keySet()) {
                Object value = args.get(key);
                if (value instanceof HttpServletResponse || value instanceof HttpServletRequest || value instanceof MultipartFile) {
                    continue;
                }
                paramsJson.put(key, value);
            }
            return paramsJson.toJSONString();
        }

        @Override
        protected void buildExtraDetails(BaseAuditLogModel auditLog) {
            HttpAuditLogModel httpAuditLogModel = (HttpAuditLogModel) auditLog;
            httpAuditLogModel.setUid(this.uid);
            httpAuditLogModel.setLevel(this.level);
            httpAuditLogModel.setUserName(this.userName);
            httpAuditLogModel.setSpendTime(this.spendTime);
            httpAuditLogModel.setServiceName(this.serviceName);
            httpAuditLogModel.setExceptionDetail(this.errorStack);
            if (principal != null) {
                httpAuditLogModel.setUserName(principal.getUsername());
//            httpAuditLogModel.setUid(principal.getId());
            }
        }


        public Builder info() {
            this.level = Level.INFO;
            return this;
        }

        public Builder error() {
            this.level = Level.ERROR;
            return this;
        }

        public Builder errorStack(String errorStack) {
            this.errorStack = errorStack;
            return this;
        }

        public Builder spentTime(Long spentTime) {
            this.spendTime = spentTime;
            return this;
        }

        public Builder serviceName(String service) {
            this.serviceName = service;
            return this;
        }

        public Builder principal(UserDetails principal) {
            this.principal = principal;
            return this;
        }

        public static Builder getBuilder(JoinPoint joinPoint) {
            HttpAuditLogModel httpAuditLogModel = new HttpAuditLogModel();
            return new Builder(joinPoint, httpAuditLogModel);
        }
    }
}
