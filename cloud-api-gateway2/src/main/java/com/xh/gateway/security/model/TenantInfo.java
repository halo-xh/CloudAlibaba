package com.xh.gateway.security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Xiao Hong
 * @since 2023-02-08
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TenantInfo {

    private String id;

    private String name;

    private String displayName;

    private Boolean enabled;


}
