package com.example.cloudsimple.jetcache;


import com.alicp.jetcache.anno.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Slf4j
@Service
public class TestCacheService {


    @Autowired
    private TestDO2Mapper do2Mapper;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public TestDO2 create(String name, Integer value) {
        stringRedisTemplate.opsForValue().setIfAbsent(name, String.valueOf(value));
        long id = snowflakeIdWorker.nextId();
        TestDO2 testDO = new TestDO2();
        testDO.setId(id);
        testDO.setName(name);
        testDO.setValue(value);
        testDO.setVersion(0);
        do2Mapper.insert(testDO);
        return testDO;
    }


    /**
     *
     * 缓存的同步 需要配置
     * broadcastChannel: jacacheChannel 全局配置
     * syncLocal = true 没有全局配置需要配置在Cached注解中
     *
     *
     * @param id
     * @return
     */
    @Cached(name = "testdo2:", localExpire = 600, expire = 600, localLimit = 50, cacheType = CacheType.BOTH, key = "#id", syncLocal = true)
    @CachePenetrationProtect
    public TestDO2 findById(Long id) {
        return do2Mapper.selectById(id);
    }


    @CacheInvalidate(name = "testdo2:", key = "#id")
    public void delete(Long id) {
        do2Mapper.deleteById(id);
    }

}
