package com.fort.xrss.rss;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fort.xrock.android.adapter.BaseFragmentPagerAdapter;
import com.fort.xrss.R;
import com.fort.xrss.base.BaseFragment;

/**
 * Created by zhxcxy on 16-12-4.
 * </br>
 * tab_res_array:int[]
 */

public class ArticlePagerFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {
    ViewPager vp;
    TabLayout tabLayout;
    BaseFragmentPagerAdapter pagerAdapter;
    int[] tabResArray;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseBD(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_tab_viewpager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vp = (ViewPager) view.findViewById(R.id.tab_vp_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_vp_tablayout);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tabLayout.clearOnTabSelectedListeners();
        tabLayout.setupWithViewPager(null);

        tabLayout.removeAllTabs();
        if (pagerAdapter != null) {
            pagerAdapter.destroy();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    void parseBD(Bundle bd) {
        if (bd != null) {
            tabResArray = bd.getIntArray("tab_res_array");
        }
    }

    void initAdapter() {
        String[] titleArray = new String[tabResArray.length];
        Resources res = getResources();
        pagerAdapter = new BaseFragmentPagerAdapter(getChildFragmentManager());
        SparseArrayCompat<Fragment> list = new SparseArrayCompat<>(tabResArray.length + 2);
        for (int index = 0; index < tabResArray.length; index++) {
            int resId = tabResArray[index];
            titleArray[index] = res.getString(resId);
            Bundle bd = new Bundle(getArguments());
            bd.putInt("type", resId);
            ArticleListFragment articleListFragment = new ArticleListFragment();
            articleListFragment.setArguments(bd);
            list.put(index, articleListFragment);
        }
        pagerAdapter.setTitlelist(titleArray);
        pagerAdapter.setFragmentList(list);
        vp.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(vp);

        tabLayout.addOnTabSelectedListener(this);
        list.clear();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
