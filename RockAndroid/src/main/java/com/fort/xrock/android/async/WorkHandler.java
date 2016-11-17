package com.fort.xrock.android.async;

import android.os.HandlerThread;

/**
 * Created by zhxcxy on 16-11-11.
 */

public class WorkHandler extends HandlerThread {

    public WorkHandler(String name) {
        super(name);
    }

    public WorkHandler(String name, int priority) {
        super(name, priority);
    }

}