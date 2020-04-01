package com.carl.mvpdemo.pub.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.carl.mvpdemo.BuildConfig;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;

public class TessTwoUtils {

    private static final TessTwoUtils ourInstance = new TessTwoUtils();

    public static TessTwoUtils getInstance() {
        return ourInstance;
    }

    private TessTwoUtils() {

    }

    private  final String TAG = "TessTwoUtils";

    /**
     * TessBaseAPI初始化用到的第一个参数，是个目录。
     */
    private  final String DATAPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

    /**
     * 在DATAPATH中新建这个目录，TessBaseAPI初始化要求必须有这个目录。
     */
    private  final String tessdata = DATAPATH + File.separator + "tessdata";
    /**
     * TessBaseAPI初始化测第二个参数，就是识别库的名字不要后缀名。
     */
    private  String DEFAULT_LANGUAGE = "num";
    /**
     * assets中的文件名
     */
    private  String DEFAULT_LANGUAGE_NAME = DEFAULT_LANGUAGE + ".traineddata";
    /**
     * 保存到SD卡中的完整文件名
     */
    public  String LANGUAGE_PATH = tessdata + File.separator + DEFAULT_LANGUAGE_NAME;


    public void copyFile(Context context) {
        SDUtils.assets2SD(context, LANGUAGE_PATH, DEFAULT_LANGUAGE_NAME);
    }

    /**
     * 识别图像
     *
     * @param bitmap
     */
    public void recognition(Context context, String mobile, final Bitmap bitmap) {
        String text = "";
        if (!checkTraineddataExists()) {
            text += LANGUAGE_PATH + "不存在，开始复制\r\n";
            Log.i(TAG, "run: " + LANGUAGE_PATH + "不存在，开始复制\r\n");
            copyFile(context);
        }
        text += LANGUAGE_PATH + "已经存在，开始识别\r\n";
        Log.i(TAG, "run: " + LANGUAGE_PATH + "已经存在，开始识别\r\n");
        long startTime = System.currentTimeMillis();
        Log.i(TAG, "run: kaishi " + startTime);
        TessBaseAPI tessBaseAPI = new TessBaseAPI();
        tessBaseAPI.init(DATAPATH, DEFAULT_LANGUAGE);

        Bitmap bitmapColor = BitmapUtils.convertBitmap(bitmap);

        tessBaseAPI.setImage(bitmapColor);
        String captcha = tessBaseAPI.getUTF8Text();
        captcha = captcha.replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
        text = text + "识别结果：" + captcha;
        long finishTime = System.currentTimeMillis();
        Log.i(TAG, "run: jieshu " + finishTime);
        String finalText = text + "\r\n" + " 耗时" + (finishTime - startTime) + "毫秒";
        Log.i(TAG, finalText);
        mCallBack.setTessTwoCall(mobile, captcha);
        tessBaseAPI.end();

//        SystemUtils.saveImageToGallery(context, BuildConfig.FLAVOR + "_old" + captcha, bitmap);
        String fileName = captcha;
        if (TextUtils.isEmpty(fileName)) {
            fileName = String.valueOf(System.currentTimeMillis());
        }
//        SystemUtils.saveImageToGallery(context, BuildConfig.FLAVOR + "_" + fileName, bitmapColor);
    }

    public boolean checkTraineddataExists() {
        File file = new File(LANGUAGE_PATH);
        return file.exists();
    }

    CallBack mCallBack;

    public void setOnCallBack(CallBack callBack) {
        mCallBack = callBack;
    }

    public interface CallBack {
        void setTessTwoCall(String mobile, String captcha);
    }

}
