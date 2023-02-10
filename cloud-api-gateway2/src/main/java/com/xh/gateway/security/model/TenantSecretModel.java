package com.xh.gateway.security.model;

import lombok.Data;

/**
 * @Author: lanbo
 * @Description:
 * @Date: 2022/10/27
 */
@Data
public class TenantSecretModel {

    private ConfigModel configValue;

    private String tenantId;

    @Data
    public static class ConfigModel {

        private String tokenSecret;
    }
}
