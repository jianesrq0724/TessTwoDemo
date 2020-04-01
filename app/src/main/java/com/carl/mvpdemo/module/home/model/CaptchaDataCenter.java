package com.carl.mvpdemo.module.home.model;

import com.carl.mvpdemo.pub.network.CookieHttpClient;
import com.carl.mvpdemo.pub.utils.CommonTreeMapUtils;

import java.util.TreeMap;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

public class CaptchaDataCenter {
    private static final CaptchaDataCenter ourInstance = new CaptchaDataCenter();
    private static CaptchaService service;
    public String baseUrl = "http://ybt.90l99.cn/";

    public static CaptchaDataCenter getInstance() {
        return new CaptchaDataCenter();
    }

    private CaptchaDataCenter() {
        service = CookieHttpClient.getInstance().createService(CaptchaService.class, baseUrl);
    }

    public Flowable<ResponseBody> downloadPicFromNet(String mobile) {
        TreeMap<String, String> queryMap = new TreeMap<>();
        CommonTreeMapUtils.addTreeMap(mobile, queryMap);

//        String cookie = SPUtils.getInstance().getPhoneCookie(mobile);
        String cookie = "";
        return service.downloadPicFromNet(cookie, queryMap);
    }

}
