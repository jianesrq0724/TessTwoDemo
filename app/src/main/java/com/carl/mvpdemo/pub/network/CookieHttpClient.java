package com.carl.mvpdemo.pub.network;

import android.text.TextUtils;

import com.carl.mvpdemo.BuildConfig;
import com.carl.mvpdemo.pub.network.interceptor.CookieInterceptor;
import com.carl.mvpdemo.pub.network.interceptor.HttpLogging;
import com.carl.mvpdemo.pub.network.interceptor.ReqInterceptor;
import com.carl.mvpdemo.pub.utils.LogUtils;
import com.carl.mvpdemo.pub.utils.TimeUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Carl
 *         version 1.0
 * @since 2018/6/10
 */
public class CookieHttpClient {

    private static final CookieHttpClient INSTANCE = new CookieHttpClient();
    private Map<String, Object> mServiceMap = new HashMap<>();

    public static CookieHttpClient getInstance() {
//        return HttpClient.INSTANCE;
        // TODO: 2018/7/5 取消单例，user-anger和cookie
        return new CookieHttpClient();
    }

    private CookieHttpClient() {

    }


    public <T> T createService(Class<T> serviceClass, String baseUrl) {
        if (TextUtils.isEmpty(baseUrl)) {
            baseUrl = "";
        }

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        T service = retrofit.create(serviceClass);
        mServiceMap.put(serviceClass.getName() + baseUrl, service);
        return retrofit.create(serviceClass);
//        }

    }

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            .retryOnConnectionFailure(true)
//            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .sslSocketFactory(SSLSocketClient.getSslFactory())
            .addInterceptor(HttpLogging.getHttpLoggingInterceptor())
            .readTimeout(TimeUtils.getTimeout(), TimeUnit.MILLISECONDS)
            .connectTimeout(TimeUtils.getTimeout(), TimeUnit.MILLISECONDS)
            .addInterceptor(new ReqInterceptor())
            .addInterceptor(new CookieInterceptor())
            .build();



}
