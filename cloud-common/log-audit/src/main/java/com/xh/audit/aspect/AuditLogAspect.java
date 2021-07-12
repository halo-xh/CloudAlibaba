package com.xh.audit.aspect;

import com.xh.audit.model.AuditLogModel;
import com.xh.audit.build.AuditLogModelMessageBuilder;
import com.xh.audit.service.LogMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.event.Level;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.xh.audit.build.BuildUtils.getStackTrace;

/**
 * @author xiaohong
 * @version 1.0
 * @date 2021/7/12 16:21
 * @description
 */

@Component
@Aspect
@Slf4j
public class AuditLogAspect {

    private final LogMessageSender messageSender;


    public AuditLogAspect(LogMessageSender messageSender) {
        this.messageSender = messageSender;
    }

    /**
     * 配置切入点
     */
    @Pointcut("(@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController) || @annotation(org.springframework.kafka.annotation.KafkaListener))" +
            "&& !@annotation(com.xh.audit.annotations.AuditLogIgnore)")
    public void logPointcut() {
    }



    @Around(value = "logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        try {
            AuditLogModel auditLog = new AuditLogModel();
            auditLog.setLevel(Level.INFO);
            auditLog.setSpendTime(beginTime - System.currentTimeMillis());
            HttpServletRequest httpServletRequest = null;
            try {
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (requestAttributes != null) {
                    httpServletRequest = requestAttributes.getRequest();
                    auditLog.setReferer(httpServletRequest.getHeader("Referer"));
                }
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                // todo.
                Object principal = authentication.getPrincipal();
                AuditLogModelMessageBuilder builder = new AuditLogModelMessageBuilder(principal, httpServletRequest, joinPoint, auditLog);
                messageSender.send(builder.build());
            } catch (Exception e) {
                AuditLogModelMessageBuilder builder = new AuditLogModelMessageBuilder(null, httpServletRequest, joinPoint, auditLog);
                messageSender.send(builder.build());
            }

        } catch (Throwable throwable) {
            log.warn("Audit modules cannot work.Check the Elasticsearch configs, exception message: {}", throwable.getMessage());
        }
        return result;
    }


    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            AuditLogModel auditLog = new AuditLogModel();
            auditLog.setLevel(Level.ERROR);
            auditLog.setSpendTime(beginTime - System.currentTimeMillis());
            auditLog.setExceptionDetail(getStackTrace(e));
            RequestAttributes request = RequestContextHolder.getRequestAttributes();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal(); // todo.
            AuditLogModelMessageBuilder builder = new AuditLogModelMessageBuilder(principal, null, joinPoint, auditLog);
            messageSender.send(builder.build());
        } catch (Throwable throwable) {
            log.warn("Audit modules cannot work.Check the Elasticsearch configs, exception message: {}", throwable.getMessage());
        }

    }


}

