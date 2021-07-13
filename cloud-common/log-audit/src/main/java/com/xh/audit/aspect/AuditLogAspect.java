package com.xh.audit.aspect;

import com.xh.audit.model.BaseAuditLogModel;
import com.xh.audit.model.HttpAuditLogModel;
import com.xh.audit.model.StreamAuditLogModel;
import com.xh.audit.service.LogMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

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

    public static final String EXCEPTION_MESSAGE = "ERROR LOG SEND FAILED, exception message: {}";
    private final LogMessageSender messageSender;


    public AuditLogAspect(LogMessageSender messageSender) {
        this.messageSender = messageSender;
    }

    /**
     * 配置切入点
     */
    @Pointcut("(@within(org.springframework.stereotype.Controller) " +
            "|| @within(org.springframework.web.bind.annotation.RestController))" +
            "&& !@annotation(com.xh.audit.annotations.AuditLogIgnore)")
    public void httpLogPointcut() {
    }

    @Pointcut("@annotation(org.springframework.kafka.annotation.KafkaListener) " +
            "||@annotation(org.springframework.cloud.stream.annotation.StreamListener)" +
            "&& !@annotation(com.xh.audit.annotations.AuditLogIgnore)")
    public void streamLogPointcut() {

    }

    @Value("${spring.application.name}")
    private String serviceName;


    @Around(value = "httpLogPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        try {
            long spendTime = System.currentTimeMillis() - beginTime;
            UserDetails principal = null;
            SecurityContext securityContext = SecurityContextHolder.getContext();
            if (securityContext != null) {
                Authentication authentication = securityContext.getAuthentication();
                if (authentication != null) {
                    principal = (UserDetails) authentication.getPrincipal();
                }
            }
            BaseAuditLogModel model = HttpAuditLogModel.Builder
                    .getBuilder(joinPoint)
                    .info()
                    .principal(principal)
                    .spentTime(spendTime)
                    .serviceName(serviceName)
                    .build();
            messageSender.send(model);
        } catch (Throwable throwable) {
            log.warn(EXCEPTION_MESSAGE, throwable.getMessage());
        }
        return result;
    }

    @Around(value = "streamLogPointcut()")
    public Object logStreamAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        try {
            long spendTime = beginTime - System.currentTimeMillis();
            BaseAuditLogModel model = StreamAuditLogModel.Builder
                    .getBuilder(joinPoint)
                    .info()
                    .spentTime(spendTime)
                    .serviceName(serviceName)
                    .build();
            messageSender.send(model);
            log.info("sent audit log......");
        } catch (Throwable throwable) {
            log.warn(EXCEPTION_MESSAGE, throwable.getMessage());
        }
        return result;
    }


    @AfterThrowing(pointcut = "httpLogPointcut()", throwing = "e")
    public void logAfterHttpThrowing(JoinPoint joinPoint, Throwable e) throws Throwable {
        try {
            UserDetails principal = null;
            SecurityContext securityContext = SecurityContextHolder.getContext();
            if (securityContext != null) {
                Authentication authentication = securityContext.getAuthentication();
                if (authentication != null) {
                    principal = (UserDetails) authentication.getPrincipal();
                }
            }
            BaseAuditLogModel model = HttpAuditLogModel.Builder
                    .getBuilder(joinPoint)
                    .error()
                    .errorStack(getStackTrace(e))
                    .principal(principal)
                    .serviceName(serviceName)
                    .build();
            messageSender.send(model);
        } catch (Throwable throwable) {
            log.warn(EXCEPTION_MESSAGE, throwable.getMessage());
        }
    }

    @AfterThrowing(pointcut = "streamLogPointcut()", throwing = "e")
    public void logAfterStreamThrowing(JoinPoint joinPoint, Throwable e) throws Throwable {
        try {
            BaseAuditLogModel model = StreamAuditLogModel.Builder
                    .getBuilder(joinPoint)
                    .error()
                    .errorStack(getStackTrace(e))
                    .serviceName(serviceName)
                    .build();
            messageSender.send(model);
        } catch (Throwable throwable) {
            log.warn(EXCEPTION_MESSAGE, throwable.getMessage());
        }
    }

}

