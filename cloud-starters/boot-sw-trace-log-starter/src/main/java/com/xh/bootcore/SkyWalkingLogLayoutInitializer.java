package com.xh.bootcore;

import ch.qos.logback.classic.PatternLayout;
import org.apache.skywalking.apm.toolkit.log.logback.v1.x.mdc.LogbackMDCPatternConverter;
import org.apache.skywalking.apm.toolkit.log.logback.v1.x.mdc.TraceIdMDCPatternLogbackLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.logging.LoggingSystemFactory;
import org.springframework.boot.logging.logback.LogbackLoggingSystem;
import org.springframework.core.Ordered;

import java.util.Map;

/**
 * @author Xiao Hong
 * @since 2022-11-03
 */
public class SkyWalkingLogLayoutInitializer implements SpringApplicationRunListener, Ordered {
    private static final Logger log = LoggerFactory.getLogger(SkyWalkingLogLayoutInitializer.class);


    public SkyWalkingLogLayoutInitializer(SpringApplication sa, String[] args) {
    }


    /**
     * 需求:
     * 不使用skywalking默认的使用logback.xml配置的 {@link TraceIdMDCPatternLogbackLayout}从而识别tid
     * <p>
     * 需求产生原因:
     * 1。logback.xml无法获取到spring的配置信息
     * 2。由于问题1每个服务都需要单独写xml
     * <p>
     * 实现方式:
     * TraceIdMDCPatternLogbackLayout 需要在spring的{@link PatternLayout}被加载前加载
     * 从而使其配置的{@link LogbackMDCPatternConverter} 生效从而识别tid
     * <p>
     * 实现原理:
     * 1. 类加载顺序以及类静态成员变量知识
     * 2. 阅读源码发现 PatternLayout 最终是在 {@link LogbackLoggingSystem} 中被加载
     * 而 LogbackLoggingSystem 加载方式为spring-boot核心jar包中的spring.factories以 {@link LoggingSystemFactory}为key进行加载
     * 最后代码的执行是在SpringApplication启动生命周期中的 {@see EventPublishingRunListener#environmentPrepared(ConfigurableBootstrapContext, ConfigurableEnvironment)}
     * 方法调用,所以能在starting方法中执行无论顺序，或者也在environmentPrepared方法中执行只是要考虑顺序{@link Ordered}
     *
     * @param bootstrapContext bootstrapContext
     */
    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        Map<String, String> defaultConverterMap = PatternLayout.CONVERTER_CLASS_TO_KEY_MAP;
        defaultConverterMap.put("X", LogbackMDCPatternConverter.class.getName());
        defaultConverterMap.put("mdc", LogbackMDCPatternConverter.class.getName());
        //  same as  TraceIdMDCPatternLogbackLayout layout = new TraceIdMDCPatternLogbackLayout();
        log.info("SkyWalking LogbackMDCPatternConverter has been loaded");
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

}
