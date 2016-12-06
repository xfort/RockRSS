package com.fort.xrss.rss;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fort.xrock.android.http.callback.RssHttpCallback;
import com.fort.xrock.android.rss.RockRss;
import com.fort.xrss.R;
import com.fort.xrss.base.BaseFragment;
import com.fort.xrss.main.AppInstance;

/**
 * Created by zhxcxy on 16-11-13.
 * RSS订阅文章列表
 * </br>
 * type:int
 */
public class ArticleListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    ArticlesAdapter articlesAdapter;

    Object httpTAG;

    int pageIndex = 1;
    int type;
    RssHttpCallback rssHttpCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseBD(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_refresh, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.recycler_refresh_swipelayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_refresh_recyclerview);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(this);
        httpTAG = SystemClock.elapsedRealtime();
        initRecyclerView();
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AppInstance.getInstance().getRssSDK().cancel(httpTAG);
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(null);
        if (articlesAdapter != null) {
            articlesAdapter.destroy();
            articlesAdapter = null;
        }
        if (rssHttpCallback != null) {
            rssHttpCallback.destroy();
            rssHttpCallback = null;
        }
        swipeRefreshLayout.removeAllViews();
    }

    @Override
    public void onDestroy() {
        articlesAdapter = null;
        httpTAG = null;
        super.onDestroy();
    }

    void parseBD(Bundle bd) {
        if (bd != null) {
            type = bd.getInt("type");
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        articlesAdapter = new ArticlesAdapter();
        articlesAdapter.setContext(getActivity());
        recyclerView.setAdapter(articlesAdapter);
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    private void loadData() {
        if (rssHttpCallback == null) {
            rssHttpCallback = new RssHttpCallback() {
                @Override
                public void onPostUI(RockRss result) {
                    super.onPostUI(result);
                    if (isDestroyed()) {
                        return;
                    }
                    setAdapter(result);

                }
            };
        }
        AppInstance.getInstance().getRssSDK().loadRssArticles(httpTAG, type, rssHttpCallback);
    }

    private void setAdapter(RockRss rockRss) {
        swipeRefreshLayout.setRefreshing(false);
        if (rockRss != null && rockRss.itemList != null && !rockRss.itemList.isEmpty()) {
            articlesAdapter.dataList.addAll(rockRss.itemList);
            articlesAdapter.notifyDataSetChanged();
        }
    }
}
