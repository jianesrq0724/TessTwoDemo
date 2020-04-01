package com.carl.mvpdemo.pub.base;


import android.content.Context;

import com.carl.mvpdemo.pub.utils.RxManage;

import java.lang.ref.WeakReference;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/9
 */
public class BasePresenter<T> {

    public RxManage mRxManage = new RxManage();
    public Context mContext;

    protected WeakReference<T> mViewRef;

    public void attachView(Context context, T view) {
        mContext = context;
        mViewRef = new WeakReference<>(view);
    }

    public T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }


    public void detachView() {
        if (mViewRef != null) {
            //解除关联
            mViewRef.clear();
            mViewRef = null;
        }
        if (mContext != null) {
            mContext = null;
        }

        mRxManage.clear();
    }

}
