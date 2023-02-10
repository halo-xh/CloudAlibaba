package com.xh.gateway.security;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.xh.gateway.common.GatewayConstants;
import com.xh.gateway.config.SecurityProperties;
import com.xh.gateway.security.model.LoginUser;
import com.xh.gateway.security.model.TenantSecretModel;
import com.xh.gateway.security.model.TokenInfo;
import com.xh.gateway.security.role.UserRoleService;
import com.xh.gateway.util.JacksonJsonUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author /
 */
@Slf4j
@Component
public class TokenProvider implements InitializingBean {

    private Key key;
    private Map<String, JwtParser> tenantJwtParserMap = Maps.newHashMap();
    private ObjectMapper objectMapper = new ObjectMapper();

    private final StringRedisTemplate redisTemplate;
    private final SecurityProperties properties;
    private final UserRoleService userRoleService;


    public TokenProvider(StringRedisTemplate redisTemplate, SecurityProperties properties, UserRoleService userRoleService) {
        this.redisTemplate = redisTemplate;
        this.properties = properties;
        this.userRoleService = userRoleService;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(properties.getBase64Secret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }


    private Key getKey(String tenantId) {
        //没有租户使用默认配置解析
        if (!StringUtils.isEmpty(tenantId)) {
            String secret = getTenantSecret(tenantId);
            byte[] keyBytes = Decoders.BASE64.decode(secret);
            return Keys.hmacShaKeyFor(keyBytes);
        } else {
            return key;
        }
    }

    private JwtParser getJwtParser(String tenantId) {
        String secret = null;
        if (StringUtils.hasText(tenantId)) {
            secret = getTenantSecret(tenantId);
        } else {
            secret = properties.getBase64Secret();
        }

        JwtParser jwtParser = tenantJwtParserMap.get(secret);
        if (jwtParser == null) {
            byte[] keyBytes = Decoders.BASE64.decode(properties.getBase64Secret());
            Key key = Keys.hmacShaKeyFor(keyBytes);
            jwtParser = Jwts.parser().setSigningKey(key);
            tenantJwtParserMap.put(secret, jwtParser);
        }
        return jwtParser;
    }


    private String getTenantSecret(String tenantId) {
        String secret = "";

        Object configCache = redisTemplate.opsForValue().get(GatewayConstants.JWT_CONFIG_KEY + tenantId);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            TenantSecretModel model = objectMapper.readValue(configCache.toString(), TenantSecretModel.class);
            if (model != null && model.getConfigValue() != null) {
                secret = model.getConfigValue().getTokenSecret();
            }
        } catch (Exception e) {
            log.error("租户JWT配置异常", e);
            throw new RuntimeException("租户JWT配置异常");
        }
        Preconditions.checkState(!StringUtils.isEmpty(secret), "租户未配置token秘钥");

        return secret;
    }

    public LoginUser getAuthentication(String token, String tenantId) {
        Claims claims = getJwtParser(tenantId)
                .parseClaimsJws(token)
                .getBody();
        String toJsonStr = JacksonJsonUtil.toJsonStr(claims);
        TokenInfo tokenInfo = JacksonJsonUtil.parseToObject(toJsonStr, TokenInfo.class);
        if (tokenInfo != null) {
            LoginUser loginUser = new LoginUser();
            TokenInfo.UserInfo userInfo = tokenInfo.getUser();
            if (userInfo != null) {
                loginUser.setId(userInfo.getId());
                loginUser.setUsername(userInfo.getUsername());
                loginUser.setNickName(userInfo.getNickname());
                loginUser.setMobile(userInfo.getMobile());
                List<String> userRoles = userRoleService.getUserRoles(userInfo.getId(), tenantId);
                if (!CollectionUtils.isEmpty(userRoles)) {
                    loginUser.setAllRoles(new HashSet<>(userRoles));
                }
            }
//            Map<String, List<String>> auth = tokenInfo.getAuth();
//            if (auth != null && !CollectionUtils.isEmpty(auth.values())) {
//                Set<String> roles = auth.values().stream().flatMap(Collection::stream).map(String::valueOf).collect(Collectors.toSet());
//                loginUser.setAllRoles(roles);
//            }
            return loginUser;
        }
        return null;
    }


    public boolean validateToken(String tenantId, String authToken) {
        try {
            getJwtParser(tenantId).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.", e);
        }
        return false;
    }

}
