package com.carl.mvpdemo.pub.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public class DensityUtils {

    public static int screenWidth;

    static {
        screenWidth = 720;
    }

    public static void measure(View view, int width, int height) {
        if (0 != width) {
            setViewWidth(view, getMeasureValue(width));
        }
        if (0 != height) {
            setViewHeight(view, getMeasureValue(height));
        }
    }

    public static void setViewWidth(View view, int width) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (null == params) {
            return;
        }
        params.width = width;
        view.setLayoutParams(params);
    }


    public static void setViewHeight(View view, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (null == params) {
            return;
        }
        params.height = height;
        view.setLayoutParams(params);
    }


    public static int getMeasureValue(int px) {
        if (0 == px) {
            return 0;
        } else if (px > 0) {
            return Math.max(1, getScreenW() * px / screenWidth);
        } else {
            return Math.min(-1, getScreenW() * px / screenWidth);
        }
    }

    /**
     * 获取屏幕宽度
     *
     * @return 屏幕宽度
     */
    public static int getScreenW() {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        return dm.widthPixels;
    }


    public static void setMargins(View v, int l, int t, int r, int b) {
        setSysMargins(v, getMeasureValue(l), getMeasureValue(t), getMeasureValue(r), getMeasureValue(b));
    }

    public static void setSysMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.setLayoutParams(p);
        }
    }


}
