package com.xh.anno;


import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EventListener
public @interface TransactionMessageEventListener {

    /**
     * 需要重试
     */
    boolean needRetry() default true;

    /**
     * 重试次数
     */
    int retryTimes() default -1;

    @AliasFor(annotation = EventListener.class, attribute = "classes")
    Class<?>[] value() default {};

    @AliasFor(annotation = EventListener.class, attribute = "value")
    Class<?>[] classes() default {};

    @AliasFor(annotation = EventListener.class, attribute = "condition")
    String condition() default "";

    @AliasFor(annotation = EventListener.class, attribute = "id")
    String id() default "";

}
