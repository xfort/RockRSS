package com.fort.xrss.web;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fort.xrock.android.view.RockWebView;
import com.fort.xrock.android.weak.WeakHandler;
import com.fort.xrss.AppApplication;
import com.fort.xrss.R;
import com.fort.xrss.base.BaseActivity;

/**
 * Created by zhxcxy on 16-11-10.
 */

public class WebActivity extends BaseActivity {
    final String TAG = "WebActivity";
    WeakHandler<WebActivity> weakHandler;

    RockWebView webView;

//     Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_rock_webview);
        webView = (RockWebView) findViewById(R.id.rock_webview);
        webView.loadUrl("http://www.163.com/");
        testWeak();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppApplication.getRefwatcher(getApplicationContext()).watch(this);
        if(weakHandler!=null){
            weakHandler.removeCallbacksAndMessages(null);
        }
    }

    private void testWeak() {
        weakHandler = new WeakHandler<WebActivity>() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "WeakHandler_" + msg.what);
//                show();
                if (msg.what == 1) {
                    getWeak().test();
                }
            }
        };
        weakHandler.setWeak(this);
        for (int index = 0; index < 20; index++) {
            Message msg = Message.obtain();
            msg.what = (int) SystemClock.currentThreadTimeMillis();
            weakHandler.sendMessageDelayed(msg, 1000 * 60 * 100);
        }
//        AppInstance.getAppInstance().setWV(webView);
    }

    private void show() {
        webView.loadUrl("https://www.baidu.com/");
    }

    private void goWeb() {
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
//        runOnUiThread();
    }

    private void test() {
        Log.d(TAG, "test");
    }
}
