package com.example.cloudsimple.service;


import com.example.cloudsimple.entity.TestDO;
import com.example.cloudsimple.repo.TestDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestDOServiceImpl implements TestDOService {

    @Resource
    private TestDoRepo repo;


    @Override
    public TestDO create(TestDO testDO) {
        return repo.save(testDO);
    }
}
