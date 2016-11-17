package com.fort.xrss.main;

import android.webkit.WebView;

/**
 * Created by zhxcxy on 16-11-10.
 */

public class AppInstance {
    static AppInstance appInstance;

    public static AppInstance getAppInstance() {
        if (appInstance == null) {
            synchronized (AppInstance.class) {
                if (appInstance == null) {
                    appInstance = new AppInstance();
                }
            }
        }
        return appInstance;
    }

}
