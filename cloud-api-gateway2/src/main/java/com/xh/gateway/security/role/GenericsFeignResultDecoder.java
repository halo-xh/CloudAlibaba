package com.xh.gateway.security.role;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.model.RestResult;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.text.DateFormat;

/**
 * @Author: lb
 * @Description:
 * @Date: 2020/9/15
 */
@Component
@Slf4j
public class GenericsFeignResultDecoder implements Decoder {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DateFormat dateFormat = mapper.getDateFormat();
        mapper.setDateFormat(new CustomJackDateFormat(dateFormat));
    }

    @Override
    public Object decode(Response response, Type type) throws FeignException {
        try {
            if (response.body() == null) {
                return null;
            }
            JavaType javaType = mapper.constructType(type);
            String bodyStr = Util.toString(response.body().asReader(Util.UTF_8));
            RestResult restResult = JSON.parseObject(bodyStr, RestResult.class);
            if (type.getTypeName().equals(RestResult.class.getName())) {
                return restResult;
            } else {
                return mapper.readValue(JSON.toJSONString(restResult.getData()), javaType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
