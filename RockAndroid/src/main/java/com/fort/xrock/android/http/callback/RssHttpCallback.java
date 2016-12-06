package com.fort.xrock.android.http.callback;

import android.text.TextUtils;
import android.util.Log;

import com.fort.xrock.android.http.HttpCallback;
import com.fort.xrock.android.http.RssName;
import com.fort.xrock.android.rss.Article;
import com.fort.xrock.android.rss.RockRss;
import com.fort.xrock.android.rss.RockRssHandler;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import okhttp3.Response;


/**
 * Created by zhxcxy on 16-11-21.
 */

public class RssHttpCallback extends HttpCallback<RockRss> {
    final String TAG = "RssHttpCallback";
    int name = 0;

    @Override
    public RockRss onParse(Response response) {
        if (response != null && response.isSuccessful()) {
            try {
                String dataStr = response.body().string();
                if (TextUtils.isEmpty(dataStr)) {
                    return null;
                }
                RockRssHandler rssHandler = new RockRssHandler();
                RockRss rockRss = new RockRss();
                rockRss = rssHandler.parseData(dataStr, rockRss);
                if (rockRss != null && name > 0 && rockRss.itemList != null && !rockRss.itemList.isEmpty()) {
                    parseDateTime(rockRss, name);
                }
                return rockRss;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setRssName(int name) {
        this.name = name;
    }

    private void parseDateTime(RockRss rockRss, int name) {
        String source = null;//Tue, 29 Nov 2016 10:04:25 +0800
//        "EEE, d MMM yyyy HH:mm:ss Z"	Wed, 4 Jul 2001 12:08:56 -0700
        switch (name) {
            case RssName.OSChina:
                source = "EEE, d MMM yyyy HH:mm:ss Z";
                break;
        }
        if (TextUtils.isEmpty(source)) {
            return;
        }
        SimpleDateFormat outDate = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());

        SimpleDateFormat sourceDate = new SimpleDateFormat(source, Locale.ENGLISH);

        for (Article item : rockRss.itemList) {
            item.sourceName = "开源中国";
            Log.d(TAG, item.title + "_" + item.pubDate);
            if (!TextUtils.isEmpty(item.pubDate)) {
                try {
                    item.pubDateStr = outDate.format(sourceDate.parse(item.pubDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
