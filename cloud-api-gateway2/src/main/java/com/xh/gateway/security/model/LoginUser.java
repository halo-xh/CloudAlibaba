package com.xh.gateway.security.model;

import lombok.Data;

import java.security.Principal;
import java.util.Set;

/**
 * @author Xiao Hong
 * @since 2023-02-06
 */
@Data
public class LoginUser implements Principal {

    private Long id;

    private String username;

    private String nickName;

    private String mobile;

    private Set<String> allRoles;

    @Override
    public String getName() {
        return username;
    }
}
