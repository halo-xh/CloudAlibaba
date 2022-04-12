package com.xh.dubboconsumer;


import com.xh.clouddubbo.DubboProviderService;
import com.xh.clouddubbo.dto.DubboTest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class DubboConsumerTester {

    @DubboReference
    private DubboProviderService dubboProviderService;

    public DubboTest getById(Long id){
       return dubboProviderService.getById(id);
    }

}
