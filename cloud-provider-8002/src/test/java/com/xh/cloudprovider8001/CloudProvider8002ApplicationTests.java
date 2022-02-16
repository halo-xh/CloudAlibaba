package com.xh.cloudprovider8001;

import com.xh.cloudprovider8002.CloudProvider8002Application;
import com.xh.cloudprovider8002.sn.idgen.MysqlDistributedSequenceGenerator;
import com.xh.cloudprovider8002.sn.idgen.SequenceGenerator;
import com.xh.cloudprovider8002.sn.idgen.model.DesignResourceSequence;
import com.xh.cloudprovider8002.sn.idgen.model.Sequence;
import com.xh.cloudprovider8002.sn.service.ICmSnGenerateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CloudProvider8002Application.class})
public class CloudProvider8002ApplicationTests {

    @Resource
    private ICmSnGenerateService cmSnGenerateService;

    @Resource
    private MysqlDistributedSequenceGenerator sequenceGenerator;

    @Test
    public void create() {
//        SequenceGenerator sequenceGenerator = new MysqlDistributedSequenceGenerator(cmSnGenerateService);
//        Sequence sequence = sequenceGenerator.nextSequence("as220216");
//        DesignResourceSequence designResourceSequence = new DesignResourceSequence(sequence,"f");
//        System.out.println("Thread = " + Thread.currentThread().getId() + " --> seq = " + designResourceSequence.getSeq());
        for (int i = 0; i < 2000; i++) {
            new Thread(()->{
                Sequence sequence = sequenceGenerator.nextSequence("as220216");
                DesignResourceSequence designResourceSequence = new DesignResourceSequence(sequence,"f");
                System.out.println("Thread = " + Thread.currentThread().getId() + " --> seq = " + designResourceSequence.getSeq());
            }).start();
        }
        try {
            Thread.sleep(10000*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
