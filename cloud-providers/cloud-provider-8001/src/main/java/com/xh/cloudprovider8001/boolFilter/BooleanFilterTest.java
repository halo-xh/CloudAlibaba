package com.xh.cloudprovider8001.boolFilter;


import lombok.AllArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BooleanFilterTest {

    private RedissonClient redissonClient;


    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:63791");
        RedissonClient redissonClient = Redisson.create(config);
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
        // 初始化布隆过滤器
        bloomFilter.tryInit(200, 0.01);

        List<String> elements = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            elements.add(UUID.randomUUID().toString());
        }

        // 向布隆过滤器中添加内容
        init(bloomFilter, elements);
        // 测试检索效果
        test(bloomFilter, elements);

        redissonClient.shutdown();
    }

    public static void init(RBloomFilter<String> bloomFilter, List<String> elements) {
        for (int i = 0; i < elements.size(); i++) {
            if (i % 2 == 0) {
                bloomFilter.add(elements.get(i));
            }
        }
    }

    public static void test(RBloomFilter<String> bloomFilter, List<String> elements) {
        int counter = 0;
        for (String element : elements) {
            if (bloomFilter.contains(element)) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
