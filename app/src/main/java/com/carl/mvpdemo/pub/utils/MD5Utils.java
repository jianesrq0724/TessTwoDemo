package com.carl.mvpdemo.pub.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String encode(String paramString) {
        int i = 0;
        try {
            byte[] arrayOfByte = MessageDigest.getInstance("MD5").digest(paramString.getBytes());
            StringBuffer localStringBuffer = new StringBuffer();
            int j = arrayOfByte.length;
            while (i < j) {
                String str1 = Integer.toHexString(0xFF & arrayOfByte[i]);
                if (str1.length() == 1)
                    localStringBuffer.append(0);
                localStringBuffer.append(str1);
                i++;
            }
            String str2 = localStringBuffer.toString();
            return str2;
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            localNoSuchAlgorithmException.printStackTrace();
        }
        return null;
    }
}
