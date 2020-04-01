package com.carl.mvpdemo.pub.utils;

import java.util.TreeMap;

public class CommonTreeMapUtils {
    public static void addTreeMap(String mobile, TreeMap<String, String> queryMap) {
        queryMap.put(SPKeyUtils.USER_FLAT, mobile);
    }


    public static String urlAddUserFlat(String mobile, String url) {
        url += "?" + SPKeyUtils.USER_FLAT + "=" + mobile;
        return url;
    }

}
