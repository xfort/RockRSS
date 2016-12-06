package com.fort.xrock.android.rss;

import android.support.v4.util.Pools;

import java.util.List;

/**
 * Created by zhxcxy on 16-11-21.
 */

public class ObjPool {

    private final static Pools.SynchronizedPool<Article> articlePool = new Pools.SynchronizedPool<>(100);

    public static Article obtain() {
        Article item = articlePool.acquire();
        return item == null ? new Article() : item;
    }

    public static void recycle(Article item) {
        articlePool.release(item);
    }

    public static void recycleList(List<Article> list) {
        for (Article item : list) {
            articlePool.release(item);
        }
    }
}
