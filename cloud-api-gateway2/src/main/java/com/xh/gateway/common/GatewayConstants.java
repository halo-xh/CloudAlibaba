package com.xh.gateway.common;

import java.nio.charset.StandardCharsets;

/**
 * @author Xiao Hong
 * @since 2023-02-08
 */
public interface GatewayConstants {


    String XT_USER_ID = "XT-USER-ID";
    String X_TENANT_CODE = "X-TENANT-CODE";

    String ROLE_ADMIN = "ROLE_ADMIN";
    String PERMISSION_KEY = "PERMISSION_KEY_";
    String TENANT_PREFIX = "TENANT_CODE_";

    String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    /**
     * 默认租户
     */
    String DEFAULT_TENANT = "cloud_operation";

    byte[] UNAUTHORIZED_RESULT_JSON_BYTES =
            ("{\n"
                    + "  \"code\": \"401\","
                    + "  \"message\": \"当前账号已退出,请重新登录\""
                    + "\n}").getBytes(StandardCharsets.UTF_8);


    byte[] FORBIDDEN_RESULT_JSON_BYTES =
            ("{\n"
                    + "  \"code\": \"403\","
                    + "  \"message\": \"您暂无此功能权限，请联系协同云运营人员！\""
                    + "\n}").getBytes(StandardCharsets.UTF_8);
    String JWT_CONFIG_KEY = "JWT_PROVIDER_CONFIGS_";


}
