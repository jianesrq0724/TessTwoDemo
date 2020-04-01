package com.carl.mvpdemo.pub.utils;

import java.util.Random;
import java.util.UUID;

public class DeviceUtils {

    public static String getUUID() {
        String s = getFullUUID();
        s = s.replaceAll("-", "");
        return s;
    }

    public static String getFullUUID() {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        return s;
    }


    static String[] phoneModelArry = {"OPPO R9", "OPPO S", "OPPO R7", "OPPO A33", "OPPO 31",
            "HuaWei Mate s", "HuaWei T8100", "HuaWei T8500", "HuaWei U8120", "HuaWei U9000", "HuaWei P7", "HuaWei P8",
            "HuaWei P9", "HuaWei Mate 7", "HuaWei Mate 8", "HuaWei Mate 9", "HuaWei Mate 10",
            "vivo  Y22iL", "vivo Y11iT", "vivo X5l", "vivo Y3t", "vivo V1", "vivo s3", "vivo Y1"};

    public static String getPhoneModel() {
        int index = new Random().nextInt(phoneModelArry.length);
        String s = phoneModelArry[index];
        return s;
    }

    private static String[][] dmArray = {{"2160", "1080"}, {"1920", "1080"},
            {"1280", "720"}, {"854", "480"}};

    public static String[] getRandomDM() {
        int index = new Random().nextInt(dmArray.length);
        return dmArray[index];
    }


}
