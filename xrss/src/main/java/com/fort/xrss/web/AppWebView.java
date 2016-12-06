package com.fort.xrss.web;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.fort.xrock.android.view.RockWebView;

/**
 * Created by zhxcxy on 16-12-6.
 */

public class AppWebView extends RockWebView {

    public AppWebView(Context context) {
        this(context, null);
    }

    public AppWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AppWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

//    private void initSetting() {
//        WebSettings settings = getSettings();
//        settings.setJavaScriptEnabled(true);
//        settings.setJavaScriptCanOpenWindowsAutomatically(true);
//        settings.setAllowFileAccess(true);
//        settings.setAllowContentAccess(true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            settings.setAllowFileAccessFromFileURLs(true);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            settings.setAllowUniversalAccessFromFileURLs(true);
//        }
//        settings.setAppCacheEnabled(true);
//        settings.setDatabaseEnabled(true);
//        settings.setGeolocationEnabled(true);
//        settings.setDomStorageEnabled(true);
//    }
}
