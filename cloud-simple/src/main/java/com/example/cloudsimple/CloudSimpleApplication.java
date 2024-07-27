package com.example.cloudsimple;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.example.id.IdCreatorConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;


@Slf4j
@EnableMethodCache(basePackages = "com.example")
@SpringBootApplication
@Import(IdCreatorConfiguration.class)
public class CloudSimpleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CloudSimpleApplication.class, args);

    }

//    {
//        StringRedisTemplate template = context.getBean(StringRedisTemplate.class);
//        Runnable runnable = () -> {
//            long l = System.currentTimeMillis();
//            Object execute = template.execute(new SessionCallback<Object>() {
//                @Override
//                public Object execute(RedisOperations operations) throws DataAccessException {
//                    operations.multi();
//                    operations.opsForValue().increment("MKT_ACT_ACC" + ":" + 123 + ":" + 666 + ":" + "kuku");
//                    operations.opsForValue().increment("MKT_ACT_ACC" + ":" + 123 + ":" + 666);
//                    return operations.exec();
//                }
//            });
//            long l12 = System.currentTimeMillis();
//            System.out.println("l12 - l = " + (l12 - l) + "----" + execute);
//        };
//        for (int i = 0; i < 30; i++) {
//            System.out.println("======================================== = " + i);
//            new Thread(runnable).start();
//        }
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(template.opsForValue().get("MKT_ACT_ACC" + ":" + 123 + ":" + 666 + ":" + "kuku"));
//        System.out.println(template.opsForValue().get("MKT_ACT_ACC" + ":" + 123 + ":" + 666));
//    }

}
