package com.fort.xrss.web;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fort.xrock.android.http.callback.StringHttpCallback;
import com.fort.xrss.R;
import com.fort.xrss.base.BaseFragment;
import com.fort.xrss.main.AppInstance;

/**
 * Created by zhxcxy on 16-11-10.
 * <p>
 * </br>
 * url:String
 * <br/>
 * loadType:int(打开网页的方式,直接打开or读取源码后填充到WebView)
 */
public class WebFragment extends BaseFragment {
    AppWebView appWebView;
    Bundle bd;
    String url;
    int loadType = R.id.load_direct;
    Object httpTAG;

    StringHttpCallback stringHttpCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseBD(getArguments());
    }

    void parseBD(Bundle bd) {
        this.bd = bd;
        if (bd != null) {
            url = bd.getString("url");
            loadType = bd.getInt("loadType");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        appWebView = new AppWebView(container.getContext());
        return appWebView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        openUrl();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        if (httpTAG != null) {
            AppInstance.getInstance().getRssSDK().cancel(httpTAG);
        }
        if (stringHttpCallback != null) {
            stringHttpCallback.destroy();
            stringHttpCallback = null;
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (appWebView != null) {
            appWebView.destroy();
            appWebView = null;
        }
        super.onDestroy();
    }

    public boolean onBackPressed() {
        if (appWebView.canGoBack()) {
            appWebView.goBack();
            return true;
        }
        return false;
    }

    void openUrl() {
        switch (loadType) {
            case R.id.load_direct:
                loadDirect();
                break;
            case R.id.load_html_source:
                loadHtmlSource();
                break;
            default:
                loadDirect();
                break;
        }
    }

    void loadDirect() {
        appWebView.loadUrl(url);
    }

    void loadHtmlSource() {
        if (httpTAG == null) {
            httpTAG = SystemClock.elapsedRealtime();
        }
        if (stringHttpCallback == null) {
            stringHttpCallback = new StringHttpCallback() {
                @Override
                public void onPostUI(String result) {
                    super.onPostUI(result);
                    if (isDestroyed()) {
                        return;
                    }
                    setHtmlSource(result);
                }
            };
        }
        AppInstance.getInstance().getRssSDK().get(url, httpTAG, stringHttpCallback);
    }

    void setHtmlSource(String content) {
        appWebView.loadData(content, null, null);
    }

    public String getCurrentUrl(){
        return appWebView.getUrl();
    }

}
