package com.fort.xrss.main;

import android.content.Intent;
import android.os.Bundle;

import com.fort.xrss.R;
import com.fort.xrss.base.BaseActivity;
import com.fort.xrss.rss.ArticleActivity;
import com.fort.xrss.rss.ArticlePagerFragment;
import com.fort.xrss.web.WebActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goArticleACT();
//        for(int index=0;index<10;index++){
//            goWeb();
//        }
    }

    private void goWeb() {
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }

    void goArticleACT() {
        Intent intent = new Intent(this, ArticleActivity.class);
        startActivity(intent);
    }

    private void setArticleFragment() {
//        ArticleListFragment fragment = new ArticleListFragment();

        ArticlePagerFragment pagerFragment = new ArticlePagerFragment();
        Bundle bd = new Bundle();
        int[] array = {R.string.OSChina, R.string.BoLeZaiXian, R.string.CSDNGeek, R.string.CXYTouTiao,
                R.string.HuXiu, R.string.ZOL, R.string.QQ_HomeLastest, R.string.Sina_HomeLastest,
                R.string.Sina_WorldLastest, R.string.Baidu_HomeLastest, R.string.Baidu_HomeHot, R.string.Baidu_WorldLastest,
                R.string.Baidu_WorldHot};
        bd.putIntArray("tab_res_array", array);
        pagerFragment.setArguments(bd);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, pagerFragment).commitAllowingStateLoss();

    }
}
