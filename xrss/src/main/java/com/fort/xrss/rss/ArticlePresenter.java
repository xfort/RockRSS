package com.fort.xrss.rss;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fort.xrock.android.rss.Article;
import com.fort.xrss.R;

/**
 * Created by zhxcxy on 16-11-25.
 */

public class ArticlePresenter {

    LayoutInflater inflater;

    public ArticleView init(Context context, ViewGroup parentView) {
        if (inflater == null) {
            inflater = LayoutInflater.from(context);
        }
        return (ArticleView) inflater.inflate(R.layout.view_article_layout, parentView, false);
    }


    public void bindView(Article data, ArticleView view) {
        view.titleTV.setText(data.title);
        view.pubDateTV.setText(data.pubDateStr + "");
        view.fromTV.setText(data.sourceName + "");
        if (TextUtils.isEmpty(data.picUrl)) {
            view.picIV.setVisibility(View.GONE);
        }
    }

}