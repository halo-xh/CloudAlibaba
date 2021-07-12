package com.xh.api;

import com.xh.common.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xiaohong
 * @version 1.0
 * @date 2021/1/28 16:37
 * @description
 */
public interface UserFeignApi {

    @PostMapping("/provider/nacos/user")
    String getUser(@RequestBody User user);

}
