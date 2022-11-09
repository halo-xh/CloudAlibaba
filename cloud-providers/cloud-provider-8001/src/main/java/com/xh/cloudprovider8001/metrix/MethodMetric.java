package com.xh.cloudprovider8001.metrix;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Xiao Hong
 * @since 2022-11-07
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodMetric {

    String name() default "";

    String description() default "";

    String[] tags() default {};
}
