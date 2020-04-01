package com.carl.mvpdemo.pub.utils;

import android.content.Context;
import android.os.Environment;

import com.carl.mvpdemo.BuildConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/23
 */
public class FileUtils {

    /**
     * 将log日志写入sdCard
     */
    public static void writeLogToSDCard(String tag, String content) {
        String fileType = "Log";
        StringBuffer writeContent = new StringBuffer();
        //包名/tag
        writeContent.append("/").append(BuildConfig.APPLICATION_ID).append("/").append(tag).append(":");
        //内容
        writeContent.append(content);
        writeToSDCard(fileType, writeContent.toString());
    }

    public static void writeContentToSDCard(String fileType, String fileContent) {
        writeContentToSDCard(fileType, fileContent, true);
    }

    public static void writeContentToSDCard(String fileType, String fileContent, boolean addTime) {
        StringBuffer contentBuf = new StringBuffer();
        contentBuf.append("\"").append(fileContent).append("\",");
        writeToSDCard(fileType, contentBuf.toString(), addTime, false);
    }

    public static void writeSimpleContentToSDCard(String fileType, String fileContent, boolean addTime) {
        StringBuffer contentBuf = new StringBuffer();
        contentBuf.append(fileContent);
        writeToSDCard(fileType, contentBuf.toString(), addTime, false);
    }

    /**
     * 将崩溃日志写入sdCard
     */
    public static void writeCrashToSDCard(String fileContent) {
        String fileType = "Crash";
        writeToSDCard(fileType, fileContent, true);
    }


    /**
     * 默认使用追加的方式
     *
     * @param fileType    文件类型
     * @param fileContent 文件内容
     */
    public static void writeToSDCard(String fileType, String fileContent) {
        writeToSDCard(fileType, fileContent, true);
    }

    private static void writeToSDCard(String fileType, String fileContent, boolean addTime) {
        writeToSDCard(fileType, fileContent, true, addTime, false);
    }

    private static void writeToSDCard(String fileType, String fileContent, boolean addTime, boolean newline) {
        writeToSDCard(fileType, fileContent, true, addTime, newline);
    }

    private static void writeToSDCard(String fileType, String fileContent, boolean append, boolean addTime, boolean newline) {
        //如果SD卡不存在或无法使用，则无法把信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        //文件路径
        StringBuffer filePath = new StringBuffer();
        //sdCard/PDA/包名/fileType/
        filePath.append(Environment.getExternalStorageDirectory().getPath()).append("/Carl/").append(BuildConfig.APPLICATION_ID).append("/").append(fileType).append("/");
        //后缀
        String fileNameSuffix = ".txt";
        //文件名
        StringBuffer fileName = new StringBuffer();
        fileName.append(fileType).append(TimeUtils.TimeFormat2DataV2(currentTime)).append(fileNameSuffix);

        File dir = new File(filePath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(filePath.toString() + fileName);
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, append)));
            StringBuffer writeContent = new StringBuffer();

            //增加时间
            if (addTime) {
                writeContent.append(TimeUtils.TimeFormatDate(currentTime)).append("  ");
            }

            writeContent.append(fileContent);
            //判断是否换行
            if (newline) {
                writeContent.append("\n");
            }

            pw.println(writeContent);
            pw.close();
        } catch (Exception e) {

        }
    }

    private static void writeToSDCard2(String fileType, String fileContent, boolean append, boolean addTime, boolean newline) {
        //如果SD卡不存在或无法使用，则无法把信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        //文件路径
        StringBuffer filePath = new StringBuffer();
        //sdCard/PDA/包名/fileType/
        filePath.append(Environment.getExternalStorageDirectory().getPath()).append("/Carl/").append("aCap").append("/").append(fileType).append("/");
        //后缀
        String fileNameSuffix = ".txt";
        //文件名
        StringBuffer fileName = new StringBuffer();
        fileName.append(fileType).append(TimeUtils.TimeFormat2DataV2(currentTime)).append(fileNameSuffix);

        File dir = new File(filePath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(filePath.toString() + fileName);
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, append)));
            StringBuffer writeContent = new StringBuffer();

            //增加时间
            if (addTime) {
                writeContent.append(TimeUtils.TimeFormatDate(currentTime)).append("  ");
            }

            writeContent.append(fileContent);
            //判断是否换行
            if (newline) {
                writeContent.append("\n");
            }

            pw.println(writeContent);
            pw.close();
        } catch (Exception e) {

        }
    }


    public static boolean retrieveApkFromAssets(Context context, String fileName, String path) {
        boolean bRet = false;

        try {
            InputStream e = context.getAssets().open(fileName);
            File file = new File(path);
            boolean lNewFile = file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];

            int i;
            while ((i = e.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }

            fos.close();
            e.close();
            bRet = true;
        } catch (IOException var10) {
            var10.printStackTrace();
        }

        return bRet;
    }

    public static void deleteFiles(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        //1級文件刪除
        if (!file.isDirectory()) {
            file.delete();
        } else if (file.isDirectory()) {
            //2級文件列表
            String[] filelist = file.list();
            //獲取新的二級路徑
            for (String aFilelist : filelist) {
                File filessFile = new File(path + "\\" + aFilelist);
                if (!filessFile.isDirectory()) {
                    filessFile.delete();
                } else if (filessFile.isDirectory()) {
                    //遞歸調用
                    deleteFiles(path + "\\" + aFilelist);
                }
            }
            file.delete();
        }
    }





}
