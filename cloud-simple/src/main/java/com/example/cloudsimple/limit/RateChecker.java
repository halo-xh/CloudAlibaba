package com.example.cloudsimple.limit;

public interface RateChecker {
    /**
     * 检查interval时间内请求数是否超过maxRequests
     * @param key         key
     * @param interval 时间间隔，单位毫秒
     * @param maxRequests 最大请求数
     * @return
     */
    boolean check(String key, Integer interval, Long maxRequests);
}
