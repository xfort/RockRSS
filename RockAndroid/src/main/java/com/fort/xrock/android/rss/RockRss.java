package com.fort.xrock.android.rss;

import java.util.List;

/**
 * Created by zhxcxy on 16-11-2.
 */

public class RockRss {
    public String version;
    public String channelTitle, channelDes, channelLink;
    public String channelPubDate;
    public List<Article> itemList;

    public void destroy() {
        if (itemList != null) {
            itemList.clear();
        }
    }
}
