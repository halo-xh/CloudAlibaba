package com.xh.cloudconfig7001.feign;

import com.xh.api.UserFeignApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author xiaohong
 * @version 1.0
 * @date 2021/1/28 16:48
 * @description
 */
@FeignClient(name = "user-service")
public interface UserService extends UserFeignApi {
}
