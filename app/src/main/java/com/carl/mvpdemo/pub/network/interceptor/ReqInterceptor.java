package com.carl.mvpdemo.pub.network.interceptor;

import com.carl.mvpdemo.pub.utils.LogUtils;
import com.carl.mvpdemo.pub.utils.StringUtils;
import com.carl.mvpdemo.pub.utils.UrlUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Carl
 * version 1.0
 * @since 2018/9/30
 */
public class ReqInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        String method = chain.request().method();

        String url = chain.request().url().toString();
        String userFlat = UrlUtils.getUserFlat(url);
        LogUtils.i("userFlat: " + userFlat);

        Request.Builder builder = chain.request()
                .newBuilder()
                .removeHeader("User-Agent")
                .addHeader("User-Agent", StringUtils.getUserAgent(userFlat));


        Request request = builder.build();

        return chain.proceed(request);
    }


}
