package com.carl.mvpdemo.pub.network;

import android.text.TextUtils;

import com.carl.mvpdemo.pub.network.interceptor.HttpLogging;
import com.carl.mvpdemo.pub.network.interceptor.ReqInterceptor;
import com.carl.mvpdemo.pub.utils.TimeUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author Carl
 * version 1.0
 * @since 2018/6/10
 */
public class HttpClient2 {

    private static final HttpClient2 INSTANCE = new HttpClient2();
    private Map<String, Object> mServiceMap = new HashMap<>();

    public static HttpClient2 getInstance() {
//        return HttpClient.INSTANCE;
        // TODO: 2018/7/5 取消单例，user-anger和cookie
        return new HttpClient2();
    }

    private HttpClient2() {

    }

    public <T> T createService(Class<T> serviceClass, String baseUrl) {
        if (TextUtils.isEmpty(baseUrl)) {
            baseUrl = "";
        }

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(DecodeConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        T service = retrofit.create(serviceClass);
        mServiceMap.put(serviceClass.getName() + baseUrl, service);
        return retrofit.create(serviceClass);

    }

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .sslSocketFactory(SSLSocketClient.getSslFactory())
            .readTimeout(TimeUtils.getTimeout(), TimeUnit.MILLISECONDS)
            .writeTimeout(TimeUtils.getTimeout(), TimeUnit.MILLISECONDS)
//            .addInterceptor(new LoggingInterceptor())
            .addInterceptor(HttpLogging.getHttpLoggingInterceptor())
            .connectTimeout(TimeUtils.getTimeout(), TimeUnit.MILLISECONDS)
//            .addInterceptor(new ReqInterceptor())
            .build();

}
