package com.carl.mvpdemo.module.home.interfaces;

import com.carl.mvpdemo.pub.loading.interfaces.ILoading;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public interface MainI extends ILoading {

    void refreshCount();

    void showState1(String text);

    void showSmsState(String text);

    void setCountdownTimeTip(String text);

    void closePage();

}
