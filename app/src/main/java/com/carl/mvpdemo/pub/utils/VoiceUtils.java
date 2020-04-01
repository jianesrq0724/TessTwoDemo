package com.carl.mvpdemo.pub.utils;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.carl.mvpdemo.BaseApplication;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author Carl
 *         version 1.0
 * @since 2019/4/10
 */
public class VoiceUtils {

    public static void palay() {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(BaseApplication.getContext(), notification);
        r.play();
    }

    public static void delayPalay(long time) {
        Disposable subscribe = Flowable.timer(time, TimeUnit.SECONDS).compose(RxUtils.rxScheduler())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        palay();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public static void loopPalay(int count) {
        for (int i = 0; i < count; i++) {
            delayPalay(i * 6);
        }
    }
}
