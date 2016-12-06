package com.fort.xrock.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;

/**
 * Created by zhxcxy on 16-12-4.
 */

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    SparseArrayCompat<Fragment> dataList;
    String[] titleArray;

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragmentList(SparseArrayCompat<Fragment> list) {
        if (list == null || list.size() <= 0) {
            if (dataList != null) {
                dataList.clear();
            }
            return;
        }
        int len = list.size();
        if (dataList == null) {
            dataList = new SparseArrayCompat<>(len + 3);
        } else {
            dataList.clear();
        }
        for (int index = 0; index < len; index++) {
            dataList.put(index, list.valueAt(index));
        }
    }

    public void setTitlelist(String[] titleArray) {
        this.titleArray = titleArray;
    }

    @Override
    public Fragment getItem(int position) {
        return dataList.valueAt(position);
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titleArray != null && position < titleArray.length) {
            return titleArray[position];
        }
        return super.getPageTitle(position);
    }

    public void destroy() {
        if (dataList != null) {
            dataList.clear();
        }
    }
}
