package com.carl.mvpdemo.pub.utils;

import android.os.Environment;

import java.io.File;

/**
 * @author Carl
 *         version 1.0
 * @since 2019/1/25
 */
public class IdCardFileUtils {

    private static File getFiles(int iDcardIndex) {
        String path = Environment.getExternalStorageDirectory().getPath() + "/Download/sfz3";
        File file = new File(path);
        File[] files = file.listFiles();
        File file1 = files[iDcardIndex];
        return file1;
    }

    public static File getZmFile(int iDcardIndex) {
        File zmFile = null;
        File[] content = getFiles(iDcardIndex).listFiles();

        //获取正反面 3:手持，4：正面
        for (int j = 0; j < content.length; j++) {
            if (content[j].getName().contains("3")) {
                zmFile = content[j];
            }
        }
        return zmFile;
    }

    public static File getFmFile(int iDcardIndex) {
        File fmFile = null;
        File[] content = getFiles(iDcardIndex).listFiles();

        //获取正反面 3:手持，4：正面
        for (int j = 0; j < content.length; j++) {
            if (content[j].getName().contains("4")) {
                fmFile = content[j];
            }
        }
        return fmFile;
    }


    public static String getName(int iDcardIndex) {
        String name = StringUtils.trimName(getFiles(iDcardIndex).getName());
        return name;
    }


    public static String getIdCard(int iDcardIndex) {
        String name = StringUtils.trimIdCard(getFiles(iDcardIndex).getName());
        return name;
    }


}

