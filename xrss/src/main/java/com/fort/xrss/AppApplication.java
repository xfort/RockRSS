package com.fort.xrss;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by zhxcxy on 16-11-10.
 */

public class AppApplication extends Application {

    RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        installLeakCanary();
    }

    private void installLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefwatcher(Context context) {
        AppApplication application = (AppApplication) context.getApplicationContext();
        return application.refWatcher;
    }
}
