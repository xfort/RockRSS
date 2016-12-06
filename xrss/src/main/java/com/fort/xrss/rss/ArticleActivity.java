package com.fort.xrss.rss;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fort.xrss.R;
import com.fort.xrss.base.BaseActivity;

/**
 * Created by zhxcxy on 16-12-5.
 */

public class ArticleActivity extends BaseActivity {

    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        setContentView(R.layout.view_empty_relativelayout);
        setArticleVPFragment();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.intent = intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setArticleVPFragment() {
        ArticlePagerFragment pagerFragment = new ArticlePagerFragment();
        Bundle bd = new Bundle();
        int[] array = {R.string.OSChina, R.string.BoLeZaiXian, R.string.CSDNGeek, R.string.CXYTouTiao,
                R.string.HuXiu, R.string.ZOL, R.string.QQ_HomeLastest, R.string.Sina_HomeLastest,
                R.string.Sina_WorldLastest, R.string.Baidu_HomeLastest, R.string.Baidu_HomeHot, R.string.Baidu_WorldLastest,
                R.string.Baidu_WorldHot};
        bd.putIntArray("tab_res_array", array);
        pagerFragment.setArguments(bd);
        getSupportFragmentManager().beginTransaction().replace(R.id.empty_relativelayout, pagerFragment).commitAllowingStateLoss();
    }
}
