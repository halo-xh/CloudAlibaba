package com.xh.gateway.security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author Xiao Hong
 * @since 2023-02-07
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermissionModel {

    private String resourceUrl;

    private List<String> roleCodes;

    private String httpMethod;

    private String serviceName;
}

