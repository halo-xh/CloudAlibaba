package com.xh.gateway.security.role;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "xt-saas-admin-service", path = "/adminservice", contextId = "filter-admin-service-feign")
public interface AdminFeignService {

    @GetMapping("/sysusers/{userId}/roles")
    List<String> getUserRoles(@PathVariable("userId") Long userId);

}
