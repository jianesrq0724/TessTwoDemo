package com.carl.mvpdemo.pub.network;

import com.carl.mvpdemo.BuildConfig;
import com.carl.mvpdemo.pub.utils.LogUtils;
import com.carl.mvpdemo.pub.utils.StringUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author Carl
 * version 1.0
 * @since 2018/6/11
 */
public class HttpParam {
    /**
     * 将String转为RequestBody
     *
     * @param value
     * @return
     */
    public static RequestBody getRequestBody(String value) {
//        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
//        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=gb2312"), value);
//        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), value);
        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), value);

        return body;
    }

    /**
     * 把File转化成MultipartBody.Part:
     *
     * @return
     */
    public static MultipartBody.Part filesToMultipartBodyParts(File file) {
        // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        String name = "photoimg";
        if (BuildConfig.FLAVOR.contains("hbt")) {
            name = "file";
        } else if (BuildConfig.FLAVOR.contains("cft")) {
            name = "img";
        } else if ( BuildConfig.FLAVOR.contains("gzl")) {
            name = "image";
        } else if (BuildConfig.FLAVOR.contains("obt")) {
            name = "filename_file";
        } else if (BuildConfig.FLAVOR.contains("lkc")) {
            name = "qrcode";
        } else if (BuildConfig.FLAVOR.contains("dyl")) {
            name = "pic1";
        } else if ( BuildConfig.FLAVOR.contains("ybt")) {
            name = "upfile";
        } else if (BuildConfig.FLAVOR.contains("skl")) {
            name = "imgFile";
        } else if (BuildConfig.FLAVOR.contains("kuangbao")) {
            name = "pic";
        } else if ( BuildConfig.FLAVOR.contains("jpg")) {
            name = "file_upload";
        } else if (BuildConfig.FLAVOR.contains("gzl")) {
            name = "front";
        } else if (BuildConfig.FLAVOR.contains("tggj")) {
            name = "file1";
        }

        String fileName = file.getName();
        if (StringUtils.exitHanzi(fileName)) {
            LogUtils.e(fileName);
            fileName = "hh.jpg";
        }
        MultipartBody.Part part = MultipartBody.Part.createFormData(name, fileName, requestBody);
        return part;
    }
}
