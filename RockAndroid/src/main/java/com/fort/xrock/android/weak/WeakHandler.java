package com.fort.xrock.android.weak;

import java.lang.ref.WeakReference;

/**
 * Created by zhxcxy on 16-11-9.
 * 弱引用的Handler,防止内存泄漏
 */
public class WeakHandler<T> extends android.os.Handler {
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
        removeCallbacksAndMessages(null);
    }
}
