package com.carl.mvpdemo.module.home.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.carl.mvpdemo.R;
import com.carl.mvpdemo.module.home.model.CaptchaDataCenter;
import com.carl.mvpdemo.pub.base.BaseActivity;
import com.carl.mvpdemo.pub.base.BasePresenter;
import com.carl.mvpdemo.pub.utils.BitmapUtils;
import com.carl.mvpdemo.pub.utils.RxManage;
import com.carl.mvpdemo.pub.utils.RxUtils;
import com.carl.mvpdemo.pub.utils.TessTwoUtils;
import com.carl.mvpdemo.pub.utils.ToastUtils;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * @author Carl
 * version 1.0
 * @since 2019/1/31
 */
public class ModifySpActivity extends BaseActivity implements TessTwoUtils.CallBack {

    String mBuyPrice = "5.5";

    public long AUTO_TIME = 66;

    public RxManage mRxManage = new RxManage();

    static String[] mobileArray2;

    ImageView capt_iv;


    @Override
    protected void findView() {
        capt_iv = findViewById(R.id.capt_iv);
    }

    @Override
    protected void initData() {
        //TessTwo识别初始化
        TessTwoUtils.getInstance().copyFile(mContext);
        TessTwoUtils.getInstance().setOnCallBack(this);

        getCaptcha(0);



    }

    @Override
    protected void initView() {
        mToolbarManager.setToolbarTitle("test");

        capt_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCaptcha();
            }
        });

    }


    private void onClickCaptcha() {
        getCaptcha(0);
    }

    @Override
    public void setOnInteractListener() {

    }


    protected void getCaptcha(int time) {

        Disposable subscribe = Flowable.timer(time, TimeUnit.SECONDS)
                .flatMap(new Function<Long, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(Long aLong) throws Exception {
                        return CaptchaDataCenter.getInstance().downloadPicFromNet("")
                                .compose(RxUtils.rxScheduler());
                    }
                })
                .compose(RxUtils.rxScheduler())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {

                        byte[] bytes = responseBody.bytes();
                        String base64Str = Base64.encodeToString(bytes, Base64.NO_WRAP);

                        byte[] decodedString = Base64.decode(base64Str, Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        capt_iv.setImageBitmap(decodedByte);


                        Bitmap bitmap = BitmapUtils.base64ToBitmap(base64Str);
                        TessTwoUtils.getInstance().recognition(mContext, "15526134626", bitmap);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });

        mRxManage.add(subscribe);

    }


    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_sp;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxManage.clear();
    }


    @Override
    public void setTessTwoCall(String mobile, String captcha) {
        if (TextUtils.isEmpty(captcha)) {
            getCaptcha(2);
        } else {
            ToastUtils.showLong(captcha);
        }
    }
}
