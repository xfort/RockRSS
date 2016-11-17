package com.fort.xrock.android.http;

import java.lang.ref.WeakReference;
import java.util.List;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

/**
 * Created by zhxcxy on 16-11-13.
 */

public class HttpCancelRun implements Runnable {
    WeakReference<OkHttpClient> weakHttp;
    Object tag;

    public HttpCancelRun(OkHttpClient okhttp, Object tag) {
        this.tag = tag;
        weakHttp = new WeakReference<OkHttpClient>(okhttp);
    }

    @Override
    public void run() {
        if (weakHttp != null) {
            OkHttpClient okhttp = weakHttp.get();
            Dispatcher dispatcher = okhttp.dispatcher();
            List<Call> runningList = dispatcher.runningCalls();
            if (runningList != null && !runningList.isEmpty()) {
                for (Call item : runningList) {
                    if (item.request().tag().equals(tag)) {
                        item.cancel();
                    }
                }
            }

            List<Call> queuedList = dispatcher.queuedCalls();
            if (queuedList != null && !queuedList.isEmpty()) {
                for (Call item : queuedList) {
                    if (item.request().tag().equals(tag)) {
                        item.cancel();
                    }
                }
            }
        }

    }
}
