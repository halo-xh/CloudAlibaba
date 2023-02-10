package com.xh.gateway.security.role;

import java.util.List;

/**
 * @Author: lanbo
 * @Description:
 * @Date: 2022/11/15
 */
public interface UserRoleService {

    List<String> getUserRoles(Long userId, String tenantId);

}
