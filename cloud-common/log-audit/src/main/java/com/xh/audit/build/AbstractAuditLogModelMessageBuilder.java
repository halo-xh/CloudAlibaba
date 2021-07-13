package com.xh.audit.build;

import com.xh.audit.model.BaseAuditLogModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaohong
 * @version 1.0
 * @date 2021/7/12 17:46
 * @description
 */
public abstract class AbstractAuditLogModelMessageBuilder {

    private final JoinPoint joinPoint;

    private final BaseAuditLogModel auditLog;


    public AbstractAuditLogModelMessageBuilder(JoinPoint joinPoint, BaseAuditLogModel auditLog) {
        this.joinPoint = joinPoint;
        this.auditLog = auditLog;
    }


    public BaseAuditLogModel build() {
        // build private info first
        buildExtraDetails(auditLog);
        // common info
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // interface info
        Api classAuditMetadata = joinPoint.getTarget().getClass().getAnnotation(Api.class);
        ApiOperation methodAuditMetadata = method.getAnnotation(ApiOperation.class);
        if (methodAuditMetadata != null) {
            StringBuilder descption = new StringBuilder("");
            if (classAuditMetadata != null) {
                descption.append(classAuditMetadata.value());
                descption.append(Arrays.toString(classAuditMetadata.tags()));
                descption.append("-->");
            }
            descption.append(methodAuditMetadata.value());
            auditLog.setDescription(descption.toString());
        }
        // method info
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        auditLog.setMethod(methodName);
        // argument info
        Object[] argValues = joinPoint.getArgs();
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Map<String, Object> argMap = new HashMap<>(argNames.length);
        for (int i = 0; i < argNames.length; i++) {
            argMap.put(argNames[i], argValues[i]);
        }
        String params = buildParams(argMap);
        auditLog.setParams(params);
        // create date
        auditLog.setCreateTime(System.currentTimeMillis());
        return auditLog;
    }

    protected abstract String buildParams(Map<String, Object> args);


    protected abstract void buildExtraDetails(BaseAuditLogModel auditLog);

}
