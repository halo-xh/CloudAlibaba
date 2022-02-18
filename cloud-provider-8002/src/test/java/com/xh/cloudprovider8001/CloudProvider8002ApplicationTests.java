package com.xh.cloudprovider8001;

import com.xh.cloudprovider8002.CloudProvider8002Application;
import com.xh.cloudprovider8002.sn.idgen.core.generator.MysqlDistributedSequenceGenerator;
import com.xh.cloudprovider8002.sn.idgen.core.model.Sequence;
import com.xh.cloudprovider8002.sn.idgen.core.service.SequenceDetailService;
import com.xh.cloudprovider8002.sn.idgen.designresource.DesignResourceSequence;
import com.xh.cloudprovider8002.sn.idgen.designresource.MysqlDesignResourceSnGenerator;
import com.xh.cloudprovider8002.sn.idgen.designresource.RedisDesignResourceSnGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CloudProvider8002Application.class})
public class CloudProvider8002ApplicationTests {


    @Autowired
    private SequenceDetailService cmSnGenerateService;

    @Autowired
    private MysqlDesignResourceSnGenerator sequenceGenerator;

    @Autowired
    private RedisDesignResourceSnGenerator redisDesignResourceSnGenerator;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Test
    public void create() {
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < 30; j++) {
                    String sequence = sequenceGenerator.getSn("ADW" + finalI, false);
                    System.out.println("Thread = " + Thread.currentThread().getId() + " --> seq = " + sequence);
                }
            }).start();
        }
        try {
            Thread.sleep(10000 * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
