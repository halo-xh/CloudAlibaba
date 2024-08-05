package com.xh.gateway.security.model;

import lombok.Data;


@Data
public class TenantSecretModel {

    private ConfigModel configValue;

    private String tenantId;

    @Data
    public static class ConfigModel {

        private String tokenSecret;
    }
}
