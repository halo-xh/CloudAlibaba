package com.xh.filter;

import io.netty.util.internal.PlatformDependent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class DirectMemoryReporter {


   private AtomicLong o;

    // io.netty.util.internal.PlatformDependent.DIRECT_MEMORY_COUNTER

    @SneakyThrows
    @PostConstruct
    public void getRef(){
        Field field = ReflectionUtils.findField(PlatformDependent.class, "DIRECT_MEMORY_COUNTER");
        field.setAccessible(true);
        o = (AtomicLong) field.get(PlatformDependent.class);
    }

    @Scheduled(fixedDelay = 2000)
    public void rep(){
        long l = o.get();
        log.error("DIRECT_MEMORY_COUNTER:{} KB", (l/1024));
    }

}
