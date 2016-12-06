package com.fort.xrock.android.http.callback;

import com.fort.xrock.android.http.HttpCallback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by zhxcxy on 16-12-6.
 */

public class StringHttpCallback extends HttpCallback<String> {

    @Override
    public String onParse(Response response) {
        String resStr = null;

        try {
            if (response != null && response.isSuccessful()) {
                if (response.body() != null) {
                    try {
                        resStr = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return resStr;
    }
}
