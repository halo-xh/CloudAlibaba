package com.xh.gateway.security.role;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: lanbo
 * @Description:
 * @Date: 2022/11/15
 */
@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private AdminFeignService adminFeignService;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Override
    @Cacheable(value = "user_role_cache", key = "#userId+'_'+#tenantId", unless = "#result != null ")
    public List<String> getUserRoles(Long userId, String tenantId) {
        List<String> list = null;
        try {
            list = CompletableFuture.supplyAsync(() -> {
                log.info("异步查询用户角色信息userId:{} tenantId:{}", userId, tenantId);
                return adminFeignService.getUserRoles(userId);
            }, executor).get();
        } catch (Exception e) {
            log.error("获取用户角色失败", e);
        }
        return list;
    }
}
