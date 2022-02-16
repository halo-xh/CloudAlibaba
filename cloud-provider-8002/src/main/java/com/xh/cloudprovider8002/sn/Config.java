package com.xh.cloudprovider8002.sn;

import com.xh.cloudprovider8002.sn.idgen.MysqlDistributedSequenceGenerator;
import com.xh.cloudprovider8002.sn.idgen.SequenceGenerator;
import com.xh.cloudprovider8002.sn.service.ICmSnGenerateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaohong
 * @date 2022/2/16 16:54
 **/
@Configuration
public class Config {


    @Bean
    public SequenceGenerator sequenceGenerator(ICmSnGenerateService cmSnGenerateService){
        return new MysqlDistributedSequenceGenerator(cmSnGenerateService);
    }
}
