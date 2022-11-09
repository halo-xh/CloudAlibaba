package com.xh.dubboproider.service;

import com.xh.clouddubbo.DubboProviderService;
import com.xh.clouddubbo.dto.DubboTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@DubboService
public class DubboProviderServiceImpl implements DubboProviderService {

    @Override
    public DubboTest getById(Long id) {
        DubboTest dubboTest = new DubboTest();
        dubboTest.setId(1L);
        dubboTest.setEventType("A");
        dubboTest.setInstanceId(222L);
        return dubboTest;
    }

    @Override
    public void save(DubboTest test) {
        log.info("save DubboTest:{}", test);
    }
}
