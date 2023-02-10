package com.xh.gateway.security.model;

import lombok.Data;

import java.security.Principal;

/**
 * @author Xiao Hong
 * @since 2023-02-07
 */
@Data
public class AnonymousUser implements Principal {
    @Override
    public String getName() {
        return null;
    }
}
