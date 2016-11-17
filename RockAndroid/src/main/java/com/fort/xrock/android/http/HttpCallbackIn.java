package com.fort.xrock.android.http;

import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zhxcxy on 16-11-14.
 */

public interface HttpCallbackIn<T> extends Callback {
    public T onParse(Response response);
    public void onPostUI(T obj);
}
