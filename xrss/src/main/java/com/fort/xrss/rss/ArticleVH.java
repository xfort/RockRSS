package com.fort.xrss.rss;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fort.xrock.android.recycler.RecyclerAdapterClick;

/**
 * Created by zhxcxy on 16-11-21.
 */

public class ArticleVH extends RecyclerView.ViewHolder implements View.OnClickListener {

    ArticleView articleView;

    RecyclerAdapterClick<Void> adapterClick;

    public ArticleVH(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public ArticleView findView(int resId) {
        if (articleView == null) {
            articleView = (ArticleView) itemView.findViewById(resId);
        }
        return articleView;
    }

    public View.OnClickListener getClickListener() {
        return this;
    }

    public void setAdapterClick(RecyclerAdapterClick<Void> adapterClick) {
        this.adapterClick = adapterClick;
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        int viewType = getItemViewType();
        if (adapterClick != null) {
            adapterClick.onAdapterViewClick(itemView, v, position, viewType, null);
        }
    }
}
