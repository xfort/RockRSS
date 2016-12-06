package com.fort.xrss.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.fort.xrss.AppApplication;

/**
 * Created by zhxcxy on 16-11-10.
 */

public class BaseFragment extends Fragment {
    boolean isDestroyed = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isDestroyed = false;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isDestroyed = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppApplication.getRefwatcher(getContext().getApplicationContext()).watch(this);
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }
}
