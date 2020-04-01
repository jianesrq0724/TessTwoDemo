package com.carl.mvpdemo.pub.webview;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.ScreenUtils;
import com.carl.mvpdemo.R;
import com.carl.mvpdemo.pub.base.BaseActivity;
import com.carl.mvpdemo.pub.base.BasePresenter;
import com.carl.mvpdemo.pub.utils.LogUtils;
import com.carl.mvpdemo.pub.utils.RxManage;
import com.carl.mvpdemo.pub.utils.RxUtils;
import com.carl.mvpdemo.pub.utils.SPUtils;
import com.carl.mvpdemo.pub.utils.StringUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class WebViewActivity extends BaseActivity {
    public int mType;

    public String mCookie;

    public String mMobile;

    WebView mWebView;

    public String mLoadUrl;

    private RxManage mRxManage = new RxManage();

    public static final String EXTRA_URL = "url";

    public static final String EXTRA_TYPE = "type";

    public static final String EXTRA_COOKIE = "cookie";

    public static final String EXTRA_MOBILE = "mobile";

    public static final int TYPE_GET_COOKIE = 0x001;

    public static final int TYPE_MANUAL = 0x002;

    public static final int TYPE_WEB = 0x003;

    public static final int TYPE_INPUT_HIDDEN = 0x004;

    public static final int TYPE_Video_Click = 0x005;

    public static final int TYPE_AUTO_REFRESH = 0x006;

    public static final int TYPE_AUTO_READ = 0x007;

    public int mAutoRefreshCount;

    public static void startActivity(Context context, String url, int type) {
        startActivity(context, url, type, "");
    }

    public static void startActivity(Context context, String url, int type, String mobile) {
        startActivity(context, url, type, mobile, "");
    }

    public static void startActivity(Context context, String url, int type, String mobile, String cookie) {
        Intent intent = new Intent(context.getApplicationContext(), WebViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TYPE, type);
        intent.putExtra(EXTRA_MOBILE, mobile);
        intent.putExtra(EXTRA_COOKIE, cookie);
        context.getApplicationContext().startActivity(intent);

    }

    @Override
    protected void findView() {
        mWebView = findViewById(R.id.webView);
    }

    private void initWebViewSetting() {
        WebSettings webSetting = mWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setUserAgentString(StringUtils.getUserAgent(mMobile));
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.clearCache(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.setWebChromeClient(new WebChromeClient());
    }

    @Override
    protected void initData() {
        mLoadUrl = getIntent().getStringExtra(EXTRA_URL);
        mType = getIntent().getIntExtra(EXTRA_TYPE, 0);
        mCookie = getIntent().getStringExtra(EXTRA_COOKIE);
        mMobile = getIntent().getStringExtra(EXTRA_MOBILE);

        initWebViewSetting();
        syncCookie();
        mWebView.loadUrl(mLoadUrl);

    }


    public void syncCookie() {
        CookieSyncManager.createInstance(mContext);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);

        String[] cookieSplit = mCookie.split(";");
        for (int i = 0; i < cookieSplit.length; i++) {
            if (!TextUtils.isEmpty(cookieSplit[i])) {
                cookieManager.setCookie(mLoadUrl, cookieSplit[i]);
            }
        }
        CookieSyncManager.getInstance().sync();
        String reqWebCookie = cookieManager.getCookie(mLoadUrl);
        LogUtils.e("reqWebCookie:" + reqWebCookie);
    }


    public class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView webview, String url) {
            webview.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            if (isDestroy) {
                return;
            }

            CookieManager cookieManager = CookieManager.getInstance();
            String cookieStr = cookieManager.getCookie(url);
            LogUtils.i("webCookie", mMobile + "," + cookieStr);

            //获取cookie自动关闭页面
            if (mType == TYPE_GET_COOKIE) {
                if (!TextUtils.isEmpty(mMobile) && !TextUtils.isEmpty(cookieStr)) {
                    SPUtils.getInstance().putPhoneCookie(mMobile, cookieStr);
                }
            } else if (mType == TYPE_MANUAL) {
                if (!TextUtils.isEmpty(mMobile) && !TextUtils.isEmpty(cookieStr)) {
                    SPUtils.getInstance().putPhoneCookie(mMobile, cookieStr);
                }
            } else if (mType == TYPE_INPUT_HIDDEN) {
                view.loadUrl("javascript:window.local_obj.getValueById(document.getElementById('token').value);");
            } else if (mType == TYPE_Video_Click) {
                setSimulateClick(mWebView, ScreenUtils.getScreenWidth() / 2, ScreenUtils.getScreenHeight() / 2);
                finish();
            } else if (mType == TYPE_AUTO_REFRESH) {

            }
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            mWebView.reload();
        }
    }


    private void setSimulateClick(View view, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 1000;
        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        upEvent.recycle();
    }


    @Override
    protected void initView() {
        if (mType == TYPE_GET_COOKIE) {
            closePage(16);
        } else if (mType == TYPE_AUTO_READ) {
            closePage(20);
        } else if (mType == TYPE_MANUAL) {
            closePage(66);
        } else if (mType == TYPE_AUTO_REFRESH) {
            Disposable subscribe = Flowable.interval(60, TimeUnit.SECONDS)
                    .compose(RxUtils.rxScheduler())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            mAutoRefreshCount++;
                            LogUtils.e("mAutoRefreshCount:" + mAutoRefreshCount);
                            mWebView.getSettings().setUserAgentString(StringUtils.getUserAgent(StringUtils.getTel()));
                            mWebView.loadUrl(mLoadUrl);
                            if (mAutoRefreshCount == 1000) {
                                finish();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    });

            mRxManage.add(subscribe);
        }

    }

    private void closePage(long time) {
        LogUtils.i("closePage:" + time);
        Disposable subscribe = Flowable.timer(time, TimeUnit.SECONDS)
                .compose(RxUtils.rxScheduler())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtils.i("closePage finish, " + isDestroy);
                        if (!isDestroy) {
                            finish();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mRxManage.add(subscribe);
    }

    @Override
    public void setOnInteractListener() {

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cleanAllCookie();
        if (mRxManage != null) {
            mRxManage.clear();
        }
    }


    private void cleanAllCookie() {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();


        CookieSyncManager.createInstance(mContext);
        CookieManager.getInstance().removeAllCookie();

    }
}
