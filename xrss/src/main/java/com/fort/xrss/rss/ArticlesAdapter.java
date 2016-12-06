package com.fort.xrss.rss;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.fort.xrock.android.recycler.RecyclerAdapterClick;
import com.fort.xrock.android.rss.Article;
import com.fort.xrss.R;
import com.fort.xrss.web.WebActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhxcxy on 16-11-21.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticleVH> implements RecyclerAdapterClick<Void> {

    public List<Article> dataList;
    int count = 0;

    ArticlePresenter articlePresenter;

    Activity activity;
    boolean destroy = false;


    public ArticlesAdapter() {
        setHasStableIds(true);
        articlePresenter = new ArticlePresenter();
        dataList = new LinkedList<>();
    }

    public void setContext(Activity act) {
        activity = act;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        count = dataList == null ? 0 : dataList.size();
        return count;
    }

    @Override
    public ArticleVH onCreateViewHolder(ViewGroup parent, int viewType) {
        ArticleView view = articlePresenter.init(parent.getContext(), parent);
        ArticleVH vh = new ArticleVH(view);
        vh.findView(view.getId());
        vh.setAdapterClick(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ArticleVH holder, int position) {
        articlePresenter.bindView(dataList.get(position), holder.findView(R.id.view_article));
    }


    @Override
    public void onAdapterViewClick(View itemView, View clickedView, int position, int viewType, Void obj) {
        if (destroy) {
            return;
        }
        if (dataList != null && position < count) {
            Article data = dataList.get(position);
            goArticleDetail(data);
        }
    }


    public void destroy() {
        destroy = true;
        if (dataList != null) {
            dataList.clear();
            dataList = null;
        }
        if (activity != null) {
            activity = null;
        }
    }

    void goArticleDetail(Article data) {
        if (destroy) {
            return;
        }
        Intent intent = new Intent(activity, WebActivity.class);
        intent.putExtra("url", data.link);
        activity.startActivity(intent);
    }
}
