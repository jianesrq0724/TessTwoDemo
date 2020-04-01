package com.carl.mvpdemo.pub.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Carl
 * version 1.0
 * @since 2018/12/1
 */
public class ArrayUtils2 {

    static String[][] mMobileArray = {

    };

    public static void deleteArray() {
        String[] deleteArray = {

        };
        List<String> deleteList = Arrays.asList(deleteArray);

        for (int i = 0; i < mMobileArray.length; i++) {
            String name = mMobileArray[i][0];
            String idCard = mMobileArray[i][1];
            if (!deleteList.contains(name)) {
                System.out.print("{\"" + name + "\", \"" + idCard + "\"},");
            }
        }
    }


    public static void getNoRepeatIdcardList() {
        List<String> noRepeatList = new ArrayList<>();
        System.out.println(mMobileArray.length);

        for (int i = 0; i < mMobileArray.length; i++) {
            String name = mMobileArray[i][0];
            String idCard = mMobileArray[i][1];

            if (StringUtils.isHanzi(name) && name.length() <= 3 && !noRepeatList.contains(name)) {
                noRepeatList.add(name);
                System.out.print("{\"" + name + "\", \"" + idCard + "\"},");
            }
        }
        System.out.println("\n" + noRepeatList.size());
    }

    public static void getNoRepeatList() {
        List<String> noRepeatList = new ArrayList<>();
        System.out.println(mMobileArray.length);

        for (int i = 0; i < mMobileArray.length; i++) {
            String name = mMobileArray[i][0];
            String idCard = mMobileArray[i][1];

            if (!noRepeatList.contains(name)) {
                noRepeatList.add(name);
                System.out.print("{\"" + name + "\", \"" + idCard + "\"},");
            }
        }

        System.out.println("\n" + noRepeatList.size());

    }


}















