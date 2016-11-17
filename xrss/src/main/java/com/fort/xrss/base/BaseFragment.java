package com.fort.xrss.base;

import android.support.v4.app.Fragment;

import com.fort.xrss.AppApplication;

/**
 * Created by zhxcxy on 16-11-10.
 */

public class BaseFragment extends Fragment {
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AppApplication.getRefwatcher(getContext().getApplicationContext()).watch(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
