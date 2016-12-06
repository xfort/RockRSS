package com.fort.xrss.main;

import com.fort.xrss.sdk.RssSDK;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhxcxy on 16-11-10.
 */

public class AppInstance {
    static AppInstance appInstance;
    ThreadPoolExecutor appThreadPool = null;
    static RssSDK rssSDK;

    public static AppInstance getInstance() {
        if (appInstance == null) {
            synchronized (AppInstance.class) {
                if (appInstance == null) {
                    appInstance = new AppInstance();
                }
            }
        }
        return appInstance;
    }

    public ThreadPoolExecutor getThreadPool() {
        if (appThreadPool == null) {
            int size = Runtime.getRuntime().availableProcessors();
            if (size < 4) {
                size = 4;
            }
            appThreadPool = new ThreadPoolExecutor(1, size * 10, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        }
        return appThreadPool;
    }

    public RssSDK getRssSDK() {
        if (rssSDK == null) {
            rssSDK = new RssSDK();
        }
        return rssSDK;
    }
}
