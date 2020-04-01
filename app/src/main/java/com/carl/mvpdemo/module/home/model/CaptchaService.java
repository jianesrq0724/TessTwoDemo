package com.carl.mvpdemo.module.home.model;

import com.carl.mvpdemo.pub.network.bean.ResBase;
import com.carl.mvpdemo.pub.utils.CommonTreeMapUtils;

import java.util.TreeMap;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * @author Carl
 *         version 1.0
 * @since 2018/6/11
 */
public interface CaptchaService {
    @GET("captcha.php")
    Flowable<ResponseBody> downloadPicFromNet(@Header("Cookie") String cookie, @QueryMap TreeMap<String, String> map);

}
