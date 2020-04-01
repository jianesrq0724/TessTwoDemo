package com.carl.mvpdemo.pub.utils;

import com.carl.mvpdemo.pub.loading.interfaces.ILoading;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public class RxUtils {
    public RxUtils() {
    }

    public static <T> FlowableTransformer<T, T> getScheduler(boolean isLoading, ILoading loading) {
        FlowableTransformer<T, T> transformer = null;
        if (isLoading) {
            transformer = rxSchedulerLoading(loading);
        } else {
            transformer = rxScheduler();
        }

        return transformer;
    }

    public static <T> FlowableTransformer<T, T> rxSchedulerLoading(final ILoading loading) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        if (loading != null) {
                            loading.showLoading();
                        }

                    }
                }).subscribeOn(AndroidSchedulers.mainThread()).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (loading != null) {
                            loading.dismissLoading();
                        }
                    }
                })
                        .subscribeOn(Schedulers.newThread())
                        .unsubscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> FlowableTransformer<T, T> rxScheduler() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> tObservable) {
                return tObservable
                        .subscribeOn(Schedulers.newThread())
                        .unsubscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
