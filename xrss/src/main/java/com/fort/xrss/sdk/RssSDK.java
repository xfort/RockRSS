package com.fort.xrss.sdk;

import android.support.v4.util.SparseArrayCompat;
import android.text.TextUtils;

import com.fort.xrock.android.http.RockHttp;
import com.fort.xrock.android.http.RssName;
import com.fort.xrock.android.http.callback.RssHttpCallback;
import com.fort.xrss.R;
import com.fort.xrss.main.AppInstance;

/**
 * Created by zhxcxy on 16-11-21.
 */

public class RssSDK extends RockHttp {
    final String OSChina_RSS = "http://www.oschina.net/news/rss";
    final String BoLeZaiXian = "http://blog.jobbole.com/feed/";
    final String CSDNGeek = "http://geek.csdn.net/admin/news_service/rss";
    final String CXYTouTiao = "http://www.chengxuyuan.com/feed";
    final String HuXiu = "http://www.huxiu.com/rss/0.xml";
    final String ZOL = "http://rss.zol.com.cn/news.xml";
    final String QQ_HomeNews = "http://news.qq.com/newsgn/rss_newsgn.xml";
    final String Sina_HomeNews = "http://rss.sina.com.cn/news/china/focus15.xml";
    final String Sina_WorldNews = "http://rss.sina.com.cn/news/world/focus15.xml";
    final String Baidu_HomeLastest = "http://news.baidu.com/n?cmd=4&class=civilnews&tn=rss";
    final String Baidu_WorldLastest = "http://news.baidu.com/n?cmd=4&class=internews&tn=rss";
    final String Baidu_HomeHot = "http://news.baidu.com/n?cmd=4&class=internews&tn=rss";
    final String Baidu_WorldHot = "http://news.baidu.com/n?cmd=4&class=internews&tn=rss";

    public SparseArrayCompat<String> rssMap = new SparseArrayCompat<>(20);

    public RssSDK() {
        initHttp();
        setAppThreadPool(AppInstance.getInstance().getThreadPool());
        initRssMap();
    }

    void initRssMap() {
        rssMap.put(R.string.OSChina, OSChina_RSS);
        rssMap.put(R.string.BoLeZaiXian, BoLeZaiXian);
        rssMap.put(R.string.CSDNGeek, CSDNGeek);
        rssMap.put(R.string.CXYTouTiao, CXYTouTiao);
        rssMap.put(R.string.HuXiu, HuXiu);
        rssMap.put(R.string.ZOL, ZOL);
        rssMap.put(R.string.QQ_HomeLastest, QQ_HomeNews);
        rssMap.put(R.string.Sina_HomeLastest, Sina_HomeNews);
        rssMap.put(R.string.Sina_WorldLastest, Sina_WorldNews);
        rssMap.put(R.string.Baidu_HomeLastest, Baidu_HomeLastest);
        rssMap.put(R.string.Baidu_WorldLastest, Baidu_WorldLastest);
        rssMap.put(R.string.Baidu_HomeHot, Baidu_HomeHot);
        rssMap.put(R.string.Baidu_WorldHot, Baidu_WorldHot);
    }

    public void loadRssArticles(Object tag, int type, RssHttpCallback callback) {
        String url = rssMap.get(type);
        if (TextUtils.isEmpty(url)) {
            return;
        }
        callback.setRssName(type);
        get(url, tag, callback);
    }

    public void loadArticlesOSChina(Object tag, RssHttpCallback callback) {
        callback.setRssName(RssName.OSChina);
        get(OSChina_RSS, tag, callback);
    }


}
