package com.example.id;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;

@Configuration
@ComponentScan(value = "com.example.id")
@EnableConfigurationProperties(value = IdCreatorProperty.class)
public class IdCreatorConfiguration {



    @Bean
    public JdbcTemplate getIdCreatorJdbcTemplate(IdCreatorProperty idCreatorProperty) {
        return new JdbcTemplate(DataSourceBuilder.create()
                .driverClassName(idCreatorProperty.getJdbcDriverClassName())
                .password(idCreatorProperty.getJdbcPassword())
                .url(idCreatorProperty.getJdbcUrl())
                .username(idCreatorProperty.getJdbcUsername()).build());
    }

    @Bean
    public Jedis getJedis(IdCreatorProperty idCreatorProperty) {
        Jedis jedis = new Jedis(idCreatorProperty.getRedisHost(), idCreatorProperty.getRedisPort(), idCreatorProperty.getRedisTimeout());
        if (idCreatorProperty.getRedisPassword() != null && !"".equals(idCreatorProperty.getRedisPassword().trim())) {
            jedis.auth(idCreatorProperty.getRedisPassword());
        }
        jedis.select(idCreatorProperty.getRedisDbIndex());
        return jedis;
    }


}
