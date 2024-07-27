package com.example.id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class IdCreatorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Jedis jedis;

    @Autowired
    private IdCreatorProperty idCreatorProperty;

    private String offsetRedisKey;
    private String lastTimeRedisKey;
    private String shortOffsetRedisKey;
    private String shortLastTimeRedisKey;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();


    private String getOffsetRedisKey(boolean shortFlag) {
        return shortFlag ? this.shortOffsetRedisKey : this.offsetRedisKey;
    }

    private String getLastTimeRedisKey(boolean shortFlag) {
        return shortFlag ? this.shortLastTimeRedisKey : this.lastTimeRedisKey;
    }

    public String getOffset(boolean shortFlag) {
        return this.jedis.get(this.getOffsetRedisKey(shortFlag));
    }

    public String getLastTime(boolean shortFlag) {
        return this.jedis.get(this.getLastTimeRedisKey(shortFlag));
    }

    public void updateLastTime(final Long lastTime, final boolean shortFlag) {
        this.executorService.submit(() -> {
            IdCreatorService.this.jedis.set(IdCreatorService.this.getLastTimeRedisKey(shortFlag), lastTime.toString());
        });
    }

    public void updateOffset(final String offset, final boolean shortFlag) {
        this.executorService.submit(() -> {
            IdCreatorService.this.jedis.set(IdCreatorService.this.getOffsetRedisKey(shortFlag), offset);
        });
    }

    public void initRedisKey(String workerId) {
        String sectionId = idCreatorProperty.getSectionId();
        this.offsetRedisKey = "idCreator:" + sectionId + ":" +  workerId + ":offset";
        this.shortOffsetRedisKey = "idCreator:short:" + sectionId + ":" +  workerId + ":offset";
        this.lastTimeRedisKey = "idCreator:" + sectionId + ":" +  workerId + ":lastTime";
        this.shortLastTimeRedisKey = "idCreator:short:" + sectionId + ":" +  workerId + ":lastTime";
    }


    public String getWorkerId() {
        String macAddress = getMacAddress() + ":" + idCreatorProperty.getServiceName();
        String sql = "select id from id_worker_register where worker_mac = ?";

        Integer i;
        try {
            i = this.jdbcTemplate.queryForObject(sql, Integer.class, macAddress);
        } catch (EmptyResultDataAccessException var5) {
            i = null;
        }

        if (i == null) {
            int j = this.jdbcTemplate.update("INSERT INTO id_worker_register VALUES(?, ?)", null, macAddress);
            if (j != 1) {
                throw new RuntimeException("save macAddress fail");
            }

            i = this.jdbcTemplate.queryForObject(sql, Integer.class, macAddress);
        }

        return i.toString();
    }


    public void destroy() {
        this.jedis.close();
    }

    private static String getMacAddress() {
        StringBuilder sb = new StringBuilder();

        try {
            Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();

            while (el.hasMoreElements()) {
                byte[] mac = el.nextElement().getHardwareAddress();
                if (mac != null) {
                    StringBuilder builder = new StringBuilder();
                    for (byte b : mac) {
                        builder.append(toHex(b));
                        builder.append("-");
                    }
                    builder.deleteCharAt(builder.length() - 1);
                    sb.append(builder).append(":");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("get mac address fail");
        }

        String s = sb.toString();
        if (s.endsWith(":")) {
            s = s.substring(0, s.length() - 1);
        }

        return s;
    }


    private static String toHex(byte buf) {
        int n = buf >= 0 ? buf : 256 + buf;
        String str = Integer.toHexString(n);
        return str.toUpperCase();
    }


}

