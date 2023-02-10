package com.xh.gateway.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ExceptionHandler extends DefaultErrorWebExceptionHandler {

    public ExceptionHandler(ErrorAttributes errorAttributes, WebProperties resourceProperties,
                            ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties.getResources(), errorProperties, applicationContext);
    }

    /**
     * 获取异常属性
     */
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Throwable error = super.getError(request);
        return this.buildMessage(error);
    }

    /**
     * 指定响应处理方法为JSON处理的方法
     *
     * @param errorAttributes
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        return HttpStatus.SERVICE_UNAVAILABLE.value();
    }

    /**
     * 构建异常信息
     *
     * @param e
     * @return
     */
    private Map<String, Object> buildMessage(Throwable e) {
        log.error("xt Gateway boot not found  Exceptions: ", e);
        HashMap<String, Object> resMap = new HashMap<>();
        if (e.getMessage().contains("Unable to find instance")) {
            resMap.put("code", "500");
            resMap.put("message", "系统繁忙,请稍后再试");
            return resMap;
        }
        resMap.put("code", "500");
        resMap.put("message", "未知异常");
        resMap.put("traceId", TraceContext.traceId());
        return resMap;
    }

}
