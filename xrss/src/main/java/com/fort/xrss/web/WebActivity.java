package com.fort.xrss.web;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.fort.xrss.R;
import com.fort.xrss.base.BaseActivity;

/**
 * Created by zhxcxy on 16-11-10.
 */

public class WebActivity extends BaseActivity implements OnClickListener {
    final String TAG = "WebActivity";
    Intent intent;
    ImageView closeIV;
    Runnable closeIVRun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        setContentView(R.layout.activity_webview);
        setWebFragment();
        findView();
    }

    void findView() {
        closeIV = (ImageView) findViewById(R.id.act_webview_close_iv);
        findViewById(R.id.act_webview_browopen_iv).setOnClickListener(this);
        initCloseIV();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.intent = intent;
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
        if (closeIV != null) {
            closeIV.clearAnimation();
            closeIV.removeCallbacks(closeIVRun);
        }
    }

    @Override
    public void onBackPressed() {
        if (!onBackPressedFragmnet()) {
            super.onBackPressed();
        }
    }

    void setWebFragment() {
        Bundle bd = intent.getExtras();
        WebFragment webFragment = new WebFragment();
        webFragment.setArguments(bd);
        getSupportFragmentManager().beginTransaction().replace(R.id.act_webview_empty_framelayout, webFragment, "webfragment").commitAllowingStateLoss();
    }

    boolean onBackPressedFragmnet() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.act_webview_empty_framelayout);
        if (fragment != null && fragment instanceof WebFragment) {
            return ((WebFragment) fragment).onBackPressed();
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_webview_close_iv:
                if (v.isShown()) {
                    finish();
                }
                break;
            case R.id.act_webview_browopen_iv:
                openBrow();
                break;
        }
    }

    void initCloseIV() {
        closeIV.setOnClickListener(this);
        closeIVRun = new Runnable() {
            @Override
            public void run() {
                closeIV.setVisibility(View.VISIBLE);
            }
        };
        closeIV.postDelayed(closeIVRun, 3000);
    }

    void openBrow() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.act_webview_empty_framelayout);
        if (fragment != null && fragment instanceof WebFragment) {
            String url = ((WebFragment) fragment).getCurrentUrl();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }
}
