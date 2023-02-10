package com.xh.gateway.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Xiao Hong
 * @since 2023-02-06
 */
@Slf4j
public class JacksonJsonUtil {


    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T parseToObject(String jsonString, Class<T> clz) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, clz);
        } catch (JsonProcessingException e) {
            log.error("parseToObject error String:{}, Class:{}", jsonString, clz.getName(), e);
        }
        return null;
    }

    public static String toJsonStr(Object o) {
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("toJsonStr error Object:{}", o, e);
        }
        return null;
    }
}
