package com.xh.gateway.security.role;

import java.util.List;


public interface UserRoleService {

    List<String> getUserRoles(Long userId, String tenantId);

}
