package com.fort.xrock.android.http;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zhxcxy on 16-11-13.
 */

public abstract class HttpCallback<T> implements HttpCallbackIn<T> {
    WeakReference<Handler> weakHandler;

    public void setHandler(Handler handler) {
        weakHandler = new WeakReference<Handler>(handler);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        e.printStackTrace();
        if (!call.isCanceled()) {
            if (weakHandler != null) {
                Handler handler = weakHandler.get();
                if (handler != null) {
                    Message msg = Message.obtain(handler, new HttpCallbackRun<T>(this, null));
                    msg.obj = call.request().tag();
                    msg.sendToTarget();
                    return;
                }
            }
        }
        clearHandler();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        try {
            if (!call.isCanceled()) {
                T resData = onParse(response);
                if (weakHandler != null) {
                    Handler handler = weakHandler.get();
                    if (handler != null) {
                        Message msg = Message.obtain(handler, new HttpCallbackRun<T>(this, resData));
                        msg.obj = call.request().tag();
                        msg.sendToTarget();
                        return;
                    }
                }
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        clearHandler();
    }

    public abstract T onParse(Response response);

    public void onPostUI(T result) {
        clearHandler();
    }


    void clearHandler() {
        if (weakHandler != null) {
            weakHandler.clear();
            weakHandler = null;
        }
    }

    public void destroy() {
        clearHandler();
    }

}
