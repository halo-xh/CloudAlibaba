package com.xh.gateway.util;

import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Xiao Hong
 * @since 2023-02-07
 */
public class HttpRequestUtil {

    public static String getHeader(ServerHttpRequest request, String key) {
        HttpHeaders headers = request.getHeaders();
        List<String> hs = headers.get(key);
        return !CollectionUtils.isEmpty(hs) ? hs.get(0) : null;
    }


    public static String getCookie(ServerHttpRequest request, String key) {
        MultiValueMap<String, HttpCookie> cookiesMap = request.getCookies();
        List<HttpCookie> cookie = cookiesMap.get(key);
        if (CollectionUtils.isEmpty(cookie)) {
            return null;
        } else {
            try {
                return URLDecoder.decode(cookie.get(0).getValue(), StandardCharsets.UTF_8.displayName());
            } catch (UnsupportedEncodingException var6) {
                return cookie.get(0).getValue();
            }
        }
    }
}
