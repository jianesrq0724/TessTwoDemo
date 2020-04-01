package com.carl.mvpdemo.pub.utils;


import com.carl.mvpdemo.BaseApplication;

/**
 * Created by ruiqin.shen
 * 类说明：数据仓库
 */

public class DataWareHouse {

    /**
     * 保存用户token ，登陆成功之后保存
     */
    public static void setBase64(String base64) {
        BaseApplication.base64 = base64;
    }

    /**
     * 获取用户token
     */
    public static String getBase64() {
        return BaseApplication.base64;
    }

}
