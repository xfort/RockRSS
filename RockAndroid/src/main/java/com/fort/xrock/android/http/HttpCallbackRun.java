package com.fort.xrock.android.http;

import java.lang.ref.WeakReference;

/**
 * Created by zhxcxy on 16-11-13.
 */

public class HttpCallbackRun<T> implements Runnable {
    WeakReference<HttpCallback<T>> weakCallback;
    WeakReference<T> weakObj;

    public HttpCallbackRun(HttpCallback<T> weakHttpCallback, T obj) {
        weakCallback = new WeakReference<HttpCallback<T>>(weakHttpCallback);
        weakObj = new WeakReference<T>(obj);
    }

    @Override
    public void run() {
        if (weakCallback != null && weakObj != null) {
            HttpCallback<T> callback = weakCallback.get();
            if (callback != null) {
                callback.onPostUI(weakObj.get());
            }
        }
    }
}