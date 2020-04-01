package com.carl.mvpdemo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import com.carl.mvpdemo.pub.crash.CrashHandler;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public class BaseApplication extends Application {

    public static String base64;

    private static Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Utils.init(mContext);
        MyThread myThread = new MyThread();
        new Thread(myThread).start();
    }

    /**
     * 耗时操作启动分线程
     */
    public class MyThread implements Runnable {

        @Override
        public void run() {
            //全局异常捕捉
            if (BuildConfig.IS_CRASH) {
                CrashHandler crashHandler = CrashHandler.getInstance();
                crashHandler.init(getApplicationContext());
            }
        }
    }

    public static Context getContext() {
        return mContext;
    }
}
