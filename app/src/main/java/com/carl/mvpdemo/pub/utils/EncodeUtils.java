package com.carl.mvpdemo.pub.utils;

import android.util.Base64;

/**
 * @author Carl
 * version 1.0
 * @since 2018/9/6
 */
public class EncodeUtils {

    public static byte[] base64Decode(final String input) {
        if (input == null || input.length() == 0) {
            return new byte[0];
        }
        return Base64.decode(input, Base64.NO_WRAP);
    }

    /**
     * Return the bytes of decode Base64-encode bytes.
     *
     * @param input The input.
     * @return the bytes of decode Base64-encode bytes
     */
    public static byte[] base64Decode(final byte[] input) {
        if (input == null || input.length == 0) {
            return new byte[0];
        }
        return Base64.decode(input, Base64.NO_WRAP);
    }

    /**
     * Return Base64-encode string.
     *
     * @param input The input.
     * @return Base64-encode string
     */
    public static String base64Encode2String(final byte[] input) {
        if (input == null || input.length == 0) {
            return "";
        }
        return Base64.encodeToString(input, Base64.NO_WRAP);
    }
}
