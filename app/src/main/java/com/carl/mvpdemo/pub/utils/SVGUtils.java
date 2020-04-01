package com.carl.mvpdemo.pub.utils;

import android.graphics.drawable.Drawable;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGBuilder;

/**
 * @author Carl
 * version 1.0
 * @since 2018/9/6
 */
public class SVGUtils {
    public static SVG getSVG(String svgData) {
        SVG svg = new SVGBuilder().readFromString(svgData).build();
        return svg;
    }

    public static Drawable svgData2Drawable(String svgData) {
        return getSVG(svgData).getDrawable();
    }
}
