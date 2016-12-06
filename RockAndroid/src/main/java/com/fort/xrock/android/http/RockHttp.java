package com.fort.xrock.android.http;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhxcxy on 16-11-9.
 */

public class RockHttp {

    OkHttpClient okHttpClient;
    ExecutorService appThreadPool;
    final Handler handler = new Handler(Looper.getMainLooper());

    public void initHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.addInterceptor(new RockInterceptor());
        builder.addNetworkInterceptor(new RockNetworkInterceptor());
        okHttpClient = builder.build();
    }

    public void setAppThreadPool(ExecutorService executorService) {
        appThreadPool = executorService;
    }

    public <T> void get(String url, Object tag, HttpCallback<T> callback) {
        get(url, tag, null, callback);
    }

    public <T> void get(String url, Object tag, Map<String, String> header, HttpCallback<T> callback) {
        doRequestAsync(buildRequest("get", url, header, null, tag).build(), callback);
    }


    public <T> void postForm(String url, Object tag, Map<String, String> body, HttpCallback<T> callback) {
        postForm(url, tag, null, body, callback);
    }


    public <T> void postForm(String url, Object tag, Map<String, String> header, Map<String, String> body, HttpCallback<T> callback) {
        if (body != null && !body.isEmpty()) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> item : body.entrySet()) {
                builder.add(item.getKey(), item.getValue());
            }
            doRequestAsync(buildRequest("post", url, header, builder.build(), tag).build(), callback);
        }
    }

    public <T> void postMultipartFile(String url, Object tag, Map<String, String> body, Map<String, RequestBody> fileMap, HttpCallback<T> callback) {
        postMultipartFile(url, tag, null, body, fileMap, callback);
    }


    public <T> void postMultipartFile(String url, Object tag, Map<String, String> header, Map<String, String> body, Map<String, RequestBody> fileMap, HttpCallback<T> callback) {

        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (body != null && !body.isEmpty()) {
            for (Map.Entry<String, String> item : body.entrySet()) {
                builder.addFormDataPart(item.getKey(), item.getValue());
            }
        }

        if (fileMap != null && !fileMap.isEmpty()) {
            for (Map.Entry<String, RequestBody> item : fileMap.entrySet()) {
                builder.addFormDataPart(item.getKey(), null, item.getValue());
            }
        }
        doRequestAsync(buildRequest("post", url, header, builder.build(), tag).build(), callback);
    }

    private Request.Builder buildRequest(String method, String url, Map<String, String> headerMap, RequestBody body, Object tag) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (headerMap != null && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> item : headerMap.entrySet()) {
                builder.addHeader(item.getKey(), item.getValue());
            }
        }
        builder.tag(tag);
        builder.method(method, body);
        return builder;
    }

    private <T> void doRequestAsync(Request request, HttpCallback<T> callback) {
        if (callback.weakHandler == null) {
            callback.setHandler(handler);
        }
        okHttpClient.newCall(request).enqueue(callback);
    }


    private <T> T doRequestSync(Request request, HttpResponseIn<T> parser) {
        Response response = null;
        try {
            Call call = okHttpClient.newCall(request);
            response = call.execute();
            if (!call.isCanceled()) {
                return parser.parseResponse(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    public void cancel(Object tag) {
        handler.removeCallbacksAndMessages(tag);
        appThreadPool.execute(new HttpCancelRun(okHttpClient, tag));
    }
}
