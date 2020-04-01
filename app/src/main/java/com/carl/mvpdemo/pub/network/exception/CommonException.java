package com.carl.mvpdemo.pub.network.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carl
 * version 1.0
 * @since 2018/6/11
 */
public class CommonException extends Exception {
    public static final String FLAG_UNKNOWN = "1001";//异常
    public static final String FLAG_NET_ERROR = "1002";//网络异常标志

    public static Map<String, String> exApiMaps = new HashMap<>();

    static {
        exApiMaps.put(FLAG_UNKNOWN, "未标记的异常");
        exApiMaps.put(FLAG_NET_ERROR, "请求超时");
        exApiMaps.put("1004", "token失效");
    }


}
