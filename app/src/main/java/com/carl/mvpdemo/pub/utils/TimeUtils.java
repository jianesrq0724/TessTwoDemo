package com.carl.mvpdemo.pub.utils;

import com.carl.mvpdemo.BuildConfig;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/23
 */

public class TimeUtils {


    /**
     * 毫秒转化成日时分
     */
    public static String formatTime(long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        if (second > 0) {
            sb.append(second).append("秒");
        }
        return sb.toString();
    }


    /**
     * 毫秒值转化为日期 /隔开
     */
    public static String TimeFormat2DateWithChinese(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy年MM月dd日 HH:mm", Locale.CHINESE);
    }

    /**
     * 毫秒值转化为日期 /隔开
     */
    public static String TimeFormat2DateWithChinese2(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("MM/dd HH:mm", Locale.CHINESE);
    }

    /**
     * 毫秒值转化为日期 /隔开
     */
    public static String TimeFormat2DateWithChinese3(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("MM-dd HH:mm", Locale.CHINESE);
    }

    /**
     * 毫秒值转化为日期 /隔开
     */
    public static String TimeFormat2Date(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy/MM/dd HH:mm:ss", Locale.CHINESE);
    }

    public static String TimeFormatDate(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("MM/dd HH:mm:ss", Locale.CHINESE);
    }

    public static String TimeFormat3Date(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy/MM/dd HH:mm", Locale.CHINESE);
    }

    public static String TimeFormat2DataV2(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy-MM-dd");
    }

    public static String TimeFormat2DataV3(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
    }

    public static String TimeFormat2DataV4(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy-MM-dd HH:mm", Locale.CHINESE);
    }

    public static String TimeFormat4SH(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("MM/dd HH:mm", Locale.CHINESE);
    }

    public static String TimeFormat4SHInHM(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("HH:mm", Locale.CHINESE);
    }

    /**
     * 转换成小时分钟
     */
    public static String TimeFormat2HM(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("HH:mm");
    }

    public static String DateFormat2MS(long millisecond) {
        //初始化Formatter的转换格式
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(millisecond);
    }


    public static String getCurrentYMD() {
        DateTime lDateTime = new DateTime(System.currentTimeMillis());
        return lDateTime.toString("yyyy-MM-dd");
    }


    public static String getCurrentYMDHM() {
        DateTime lDateTime = new DateTime(System.currentTimeMillis());
        return lDateTime.toString("yyyy-MM-dd HH:mm");
    }

    public static String getCurrentHM() {
        DateTime lDateTime = new DateTime(System.currentTimeMillis());
        return lDateTime.toString("HH:mm");
    }

    public static String getNextYMDHM() {
        long nextCheckTimeLong = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
        DateTime lDateTime = new DateTime(nextCheckTimeLong);
        return lDateTime.toString("yyyy-MM-dd HH:mm");
    }


    public static long StringToDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (str.split(":").length == 2) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }

        Date date = new Date();
        try {
            date = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }


    public static String subStringTime(String content) {
        String timeStr = "2019-06-28 00:00:00";
        content = content.replaceAll("/", "-");
        if (content.contains("alert")) {
            content = content.split("alert")[1].split("\\)")[0];
        }

        if (content.contains("年")) {
            content = content.replaceAll("年", "-");
        }

        if (content.contains("月")) {
            content = content.replaceAll("月", "-");
        }

        if (content.contains("号")) {
            content = content.replaceAll("号", " ");
        }

        if (content.contains("日")) {
            content = content.replaceAll("日", " ");
        }

        if (content.contains("点")) {
            content = content.replaceAll("点", ":");
        }


        if (content.contains("20") && content.contains("-") && content.contains(":")) {
            int begin = content.indexOf("20");
            int end = content.lastIndexOf(":") + 3;
            timeStr = content.substring(begin, end);
        }

        System.out.println("timeStr：" + timeStr);

        return timeStr;

    }


    public static long StringHMToTime(String str) {
        long second = 12 * 60 * 60;

        String[] splitTime = str.split(":");
        if (splitTime.length > 1) {
            int hour = Integer.parseInt(splitTime[0]);
            int minute = Integer.parseInt(splitTime[1]);

            second = hour * 60 * 60 + (minute + 1) * 60;
        }

        return second;

    }


    public static long getTimeout() {
        if (BuildConfig.FLAVOR.contains("withdraw")) {
            if (BuildConfig.FLAVOR.contains("ybt")) {
                return 1200;
            } else {
                return 5 * 1000;
            }
        } else {
            if (BuildConfig.FLAVOR.contains("bpcc")) {
                return 16 * 1000;
            } else {
                return 8 * 1000;
            }
        }
    }


    public static long getSecondsLeftToday() {
        long secondsLeftToday = 86400 - DateUtils.getFragmentInSeconds(Calendar.getInstance(), Calendar.DATE);
        //earlier 10 minute
        secondsLeftToday = secondsLeftToday - 10 * 60;
        if (secondsLeftToday < 0) {
            secondsLeftToday = 0;
        }
        return secondsLeftToday;
    }


}
