package com.fort.xrock.android.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by zhxcxy on 16-11-10.
 * <p>
 * 加载手机本地的一个html页面的方法：
 * webView.loadUrl("content://com.android.htmlfileprovider/sdcard/test.html");
 */

public class RockWebView extends WebView {
    public RockWebView(Context context) {
        this(context, null);
    }

    public RockWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RockWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RockWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        initSet();
        initClient();
    }

    private void initSet() {
        WebSettings set = getSettings();
        set.setRenderPriority(WebSettings.RenderPriority.HIGH);

        set.setJavaScriptEnabled(true);
        set.setJavaScriptCanOpenWindowsAutomatically(true);
        set.setAllowFileAccess(true);
        set.setAppCacheEnabled(true);
        set.setDatabaseEnabled(true);
        set.setDomStorageEnabled(true);

        set.setPluginState(WebSettings.PluginState.ON);
        set.setUseWideViewPort(true);
        set.setLoadWithOverviewMode(true);

        set.setSupportZoom(true);
        set.setBuiltInZoomControls(true);
        set.setDisplayZoomControls(false);

        set.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局

        set.setAppCachePath(getContext().getApplicationContext().getCacheDir().getAbsolutePath());

        set.setAllowContentAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            set.setAllowFileAccessFromFileURLs(true);
            set.setAllowUniversalAccessFromFileURLs(true);
        }
    }

    private void initClient() {
        setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }
}
