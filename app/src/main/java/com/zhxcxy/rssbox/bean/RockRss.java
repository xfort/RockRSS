package com.zhxcxy.rssbox.bean;

import java.util.List;

/**
 * Created by zhxcxy on 16-11-2.
 */

public class RockRss {
    public String version;
    public String channelTitle, channelDes, channelLink;
    public String channelPubDate;
    public List<RockRssItem> itemList;

    public void test(){
        System.out.println("rock");
//        Log.d("TAG","rockrss");
    }
}
