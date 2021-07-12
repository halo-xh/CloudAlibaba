package com.xh.audit.build;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xh.audit.model.AuditLogModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

import static com.xh.audit.build.BuildUtils.getValueByKey;

/**
 * @author xiaohong
 * @version 1.0
 * @date 2021/7/12 17:46
 * @description
 */
public class AuditLogModelMessageBuilder {

    private final Object userInfo;

    private final JoinPoint joinPoint;

    private final AuditLogModel auditLog;

    private final HttpServletRequest httpServletRequest;

    public AuditLogModelMessageBuilder(@Nullable Object userInfo, HttpServletRequest httpServletRequest, JoinPoint joinPoint, AuditLogModel auditLog) {
        this.userInfo = userInfo;
        this.joinPoint = joinPoint;
        this.auditLog = auditLog;
        this.httpServletRequest = httpServletRequest;
    }

    // todo. rebuild.
    public AuditLogModel build(){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Api classAuditMetadata = joinPoint.getTarget().getClass().getAnnotation(Api.class);
        ApiOperation methodAuditMetadata = method.getAnnotation(ApiOperation.class);
        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";

        JSONObject paramsJson = new JSONObject();
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                if (argValues[i] instanceof HttpServletResponse || argValues[i] instanceof HttpServletRequest || argValues[i] instanceof MultipartFile) {
                    continue;
                }
                if (argValues[i] != null && argValues[i].getClass().getName().contains("ConsumerRecord")) {
                    JSONObject kafkaMessage = new JSONObject();
                    kafkaMessage.put("topic", getValueByKey(argValues[i], "topic"));
                    kafkaMessage.put("value", getValueByKey(argValues[i], "value"));
                    paramsJson.put(argNames[i], kafkaMessage);
                } else if (argValues[i] instanceof GenericMessage) {
                    GenericMessage messObject = (GenericMessage) argValues[i];
                    paramsJson.put(argNames[i], messObject.getPayload());
                } else {
                    paramsJson.put(argNames[i], argValues[i]);
                }
            }
        }
        auditLog.setParams(paramsJson);

        // 描述
        if (auditLog != null && methodAuditMetadata != null) {
            StringBuilder descption = new StringBuilder("");
            if (classAuditMetadata != null) {
                descption.append(classAuditMetadata.value());
                descption.append(Arrays.toString(classAuditMetadata.tags()));
                descption.append("->");
            }
            descption.append(methodAuditMetadata.value());
            auditLog.setDescription(descption.toString());
        }

        String loginPath = "login";
        if (loginPath.equals(signature.getName())) {
            try {
                assert argValues != null;
                userName = JSON.parseObject(JSON.toJSONString(argValues[0])).get("username").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        auditLog.setUid(uid);
        auditLog.setUserName(userName);
        auditLog.setBrowser(browser);
        auditLog.setCreatedAt(LocalDateTime.now());
        auditLog.setMethod(methodName);
        auditLog.setServiceName(serviceName);
        return auditLog;
    }


}
