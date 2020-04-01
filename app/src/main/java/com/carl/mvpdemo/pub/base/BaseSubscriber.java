package com.carl.mvpdemo.pub.base;

import android.text.TextUtils;

import com.carl.mvpdemo.BuildConfig;
import com.carl.mvpdemo.pub.network.bean.ResBase;
import com.carl.mvpdemo.pub.utils.CommonTreeMapUtils;
import com.carl.mvpdemo.pub.utils.LogUtils;
import com.carl.mvpdemo.pub.utils.ToastUtils;

import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.ResponseBody;

/**
 * @author Carl
 *         version 1.0
 * @since 2018/6/11
 */
public abstract class BaseSubscriber<T> extends DisposableSubscriber<T> {

    @Override
    public void onNext(T t) {

        if (t instanceof ResponseBody) {
            String s = ((ResponseBody) t).source().toString();
            LogUtils.e(s.toString());

            if (s.contains("success")) {
                onUserSuccess(t);
            } else {
                onUserFail(t);
            }
        } else if (t instanceof ResBase) {
            ResBase resBase = (ResBase) t;
            LogUtils.e(resBase.toString());

            String code = resBase.code;

            if (TextUtils.isEmpty(code)) {
                onUserSuccess(t);
            } else {
                onUserFail(t);
            }
        }
    }

    protected abstract void onUserFail(T t);

    protected abstract void onUserSuccess(T t);

}
