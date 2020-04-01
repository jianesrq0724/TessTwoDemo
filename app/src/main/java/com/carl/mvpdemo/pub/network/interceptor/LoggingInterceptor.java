package com.carl.mvpdemo.pub.network.interceptor;

import com.carl.mvpdemo.pub.utils.LogUtils;
import com.carl.mvpdemo.pub.utils.SPKeyUtils;
import com.carl.mvpdemo.pub.utils.SPUtils;
import com.carl.mvpdemo.pub.utils.StringUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author Carl
 *         version 1.0
 * @since 2018/9/30
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        //请求发起的时间
        long t1 = System.nanoTime();

        LogUtils.e("network", String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);
        //收到响应的时间
        long t2 = System.nanoTime();
        //这里不能直接使用response.body().string()的方式输出日志,因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);

        LogUtils.e("network", String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, StringUtils.unicodeToString(responseBody.string())));




        return response;
    }
}
