package com.fort.xrock.android.http;

import okhttp3.Response;

/**
 * Created by zhxcxy on 16-11-17.
 */

public interface HttpResponseIn<T> {
    public  T parseResponse(Response response);
}
