package com.carl.mvpdemo.pub.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;

import com.carl.mvpdemo.BaseApplication;
import com.carl.mvpdemo.pub.utils.bean.GoodsCodeBean;
import com.carl.mvpdemo.pub.utils.bean.IDCardBean;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Carl
 *         version 1.0
 * @since 2019/1/30
 */
public class GsonUtils {
    public static String getJson(String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = BaseApplication.getContext().getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static List<String> parseGoodsData(String result) {//Gson 解析
        List<String> detail = new ArrayList<>();
        try {
            Gson gson = new Gson();
            GoodsCodeBean goodsCodeBean = gson.fromJson(result, GoodsCodeBean.class);
            detail = goodsCodeBean.getData();
        } catch (Exception e) {

        }
        return detail;
    }

    public static List<IDCardBean.DataBean> parseIDCardData(String result) {//Gson 解析
        List<IDCardBean.DataBean> detail = new ArrayList<>();
        try {
            Gson gson = new Gson();
            IDCardBean goodsCodeBean = gson.fromJson(result, IDCardBean.class);
            detail = goodsCodeBean.getData();
        } catch (Exception e) {

        }
        return detail;
    }
}
