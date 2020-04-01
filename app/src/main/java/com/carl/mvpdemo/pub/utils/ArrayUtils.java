package com.carl.mvpdemo.pub.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Carl
 * version 1.0
 * @since 2018/12/1
 */
public class ArrayUtils {

    static String[] mMobileArray = {
    };

    static String[] mMobileArray2 = {

    };


    /**
     * 合并
     *
     * @return
     */
    public static List<String> mergeMobile() {

        List<String> noRepeatList = new ArrayList<>();
        for (int i = 0; i < mMobileArray2.length; i++) {
            noRepeatList.add(mMobileArray[i]);
            noRepeatList.add(mMobileArray2[i]);
        }
        return noRepeatList;
    }

    /**
     * 得到不重复的数组
     *
     * @return
     */
    public static List<String> getNoRepeatList() {

        List<String> noRepeatList = new ArrayList<>();

        //去重复
        for (int i = 0; i < mMobileArray.length; i++) {
            String s = mMobileArray[i];
            if (!noRepeatList.contains(s)) {
                noRepeatList.add(s);
            }
        }

        return noRepeatList;

    }


    /**
     * 数组合并
     *
     * @return
     */
    public static List<String> addArray() {

        String[] addArray = {

        };

        List<String> newList = getNoRepeatList();

        for (int i = 0; i < addArray.length; i++) {
            String s = addArray[i];
            if (!newList.contains(s)) {
                newList.add(s);
            }
        }

        return newList;
    }

    /**
     * 数组删除
     *
     * @return
     */
    public static List<String> deleteArray() {

        String[] deleteArray = {


        };

        List<String> oldList = getNoRepeatList();
        List<String> deleteList = Arrays.asList(deleteArray);
        List<String> newList = new ArrayList<>();

        //遍历原始数据
        for (int i = 0; i < oldList.size(); i++) {
            String s = oldList.get(i);
            //不需要删除则添加
            if (!deleteList.contains(s)) {
                newList.add(s);
            }
        }
        return newList;
    }


    public static List<String> randowSort() {
        List<String> list = getNoRepeatList();
        Collections.shuffle(list);
        return list;
    }


}
