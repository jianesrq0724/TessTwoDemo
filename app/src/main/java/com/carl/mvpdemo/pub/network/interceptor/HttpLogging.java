package com.carl.mvpdemo.pub.network.interceptor;

import com.carl.mvpdemo.BuildConfig;
import com.carl.mvpdemo.module.home.event.EncryptionEvent;
import com.carl.mvpdemo.pub.network.exception.EncryptionException;
import com.carl.mvpdemo.pub.utils.LogUtils;
import com.carl.mvpdemo.pub.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Carl
 * version 1.0
 * @since 2019/1/25
 */
public class HttpLogging {

    public static HttpLoggingInterceptor getHttpLoggingInterceptor() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                LogUtils.i("RetrofitLog", "retrofitBack = " + StringUtils.unicodeToString(message));

                if (StringUtils.getJiaMiFlavor()) {
                    if (message.contains("正在安全检测中") || message.contains("检测上网环境") || message.contains("安全防火墙") || message.contains("Ray ID:")) {
                        LogUtils.e("ip防火墙，需要重连");
//                        EventBus.getDefault().post(new EncryptionEvent());
                        throw new EncryptionException();
                    }
                }

                if (BuildConfig.FLAVOR.contains("ae")) {
                    if (message.contains("adfwkey=ywu28") ) {
                        LogUtils.e("ip防火墙，需要重连");

                        throw new EncryptionException();
                    }
                }
            }
        });


        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return loggingInterceptor;
    }
}
