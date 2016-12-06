package com.fort.xrss.rss;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fort.xrss.R;

/**
 * Created by zhxcxy on 16-11-21.
 */

public class ArticleView extends RelativeLayout {
    final String TAG = "ArticleView";
    public TextView titleTV, pubDateTV, readNumTV, fromTV;
    public ImageView picIV;

    public ArticleView(Context context) {
        this(context, null);

    }

    public ArticleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ArticleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ArticleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_article, this);
        titleTV = (TextView) findViewById(R.id.article_title_tv);
        pubDateTV = (TextView) findViewById(R.id.article_pubdatetime_tv);
        fromTV = (TextView) findViewById(R.id.article_sourcename_tv);
        picIV = (ImageView) findViewById(R.id.article_pic_iv);
        if (getId() == NO_ID) {
            setId(R.id.view_article);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure()");
    }
}
