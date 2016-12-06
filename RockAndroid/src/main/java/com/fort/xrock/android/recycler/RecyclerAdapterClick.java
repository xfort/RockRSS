package com.fort.xrock.android.recycler;

import android.os.Message;
import android.view.View;

/**
 * Created by zhxcxy on 16-11-21.
 */

public interface RecyclerAdapterClick<T> {
    public void onAdapterViewClick(View itemView,View clickedView, int position,int viewType,T obj);
}
