package com.fort.xrock.android.weak;

import android.os.Looper;

import java.lang.ref.WeakReference;

/**
 * Created by zhxcxy on 16-11-9.
 */

public abstract class WeakRun<T> implements Runnable {
    WeakReference<T> weakObj;

    public void setWeak(T t) {
        weakObj = new WeakReference<T>(t);
    }

    public T getWeak() {
        if (weakObj != null) {
            return weakObj.get();
        }
        return null;
    }

    public void destroy() {
        if (weakObj != null) {
            weakObj.clear();
        }
    }
}
