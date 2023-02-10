package com.xh.gateway.security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Xiao Hong
 * @since 2023-02-08
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenInfo {

    private String sub;

    private Map<String, List<String>> auth;

    private UserInfo user;

    private String uid;

    private Long exp;

    @Data
    public static class UserInfo {

        private Long id;

        private String username;

        private String nickname;

        private String mobile;

        private String loginSource;

    }
}
