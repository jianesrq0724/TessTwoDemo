package com.carl.mvpdemo.pub.network.interceptor;

import android.text.TextUtils;

import com.carl.mvpdemo.pub.utils.LogUtils;
import com.carl.mvpdemo.pub.utils.SPUtils;
import com.carl.mvpdemo.pub.utils.StringUtils;
import com.carl.mvpdemo.pub.utils.UrlUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        String url = chain.request().url().toString();
        String userFlat = UrlUtils.getUserFlat(url);
        LogUtils.i("userFlat: " + userFlat);

        if (!response.headers("Set-Cookie").isEmpty()) {
            StringBuilder cookie = new StringBuilder();
            List<String> cookieList = response.headers("Set-Cookie");
            //存在多个Set-Cookie的情况
            for (int i = 0; i < cookieList.size(); i++) {
                String[] cookieArray = cookieList.get(i).split(";");
                cookie.append(cookieArray[0]).append("; ");
            }

            if (!request.headers("Cookie").isEmpty()) {
                Headers reqHeaders = request.headers();
                String reqCookie = StringUtils.getSimpleCookie(reqHeaders.get("Cookie"));
                if (!TextUtils.isEmpty(reqCookie)) {
                    cookie.append(reqCookie);
                }
            }
            if (!TextUtils.isEmpty(cookie)) {
                SPUtils.getInstance().putPhoneCookie(userFlat, cookie.toString());
            }
        }
        return response;
    }
}
