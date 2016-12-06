package com.fort.xrock.android.rss;

import android.text.TextUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;

/**
 * Created by zhxcxy on 16-11-2.
 */

public class RockRssHandler {
    final String TAG = "RockRssHandler";
    XmlPullParser xmlParser;

    public void initParser(XmlPullParser xmlParser) {
        if (xmlParser == null) {
            try {
                this.xmlParser = XmlPullParserFactory.newInstance().newPullParser();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        } else {
            this.xmlParser = xmlParser;
        }
    }

    public RockRss parseData(String data, RockRss rss) {
        if (TextUtils.isEmpty(data)) {
            return rss;
        }
        if (xmlParser == null) {
            initParser(null);
        }
        try {
            data = data.replaceAll("&", "&amp;");

            xmlParser.setInput(new StringReader(data));

            int eventType = xmlParser.getEventType();
            String name = "", text = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        name = xmlParser.getName().trim();
                        if (name.equalsIgnoreCase("channel")) {
                            parseChannel(xmlParser, rss);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = xmlParser.getText().trim();
                        break;
                    case XmlPullParser.END_TAG:
                        name = xmlParser.getName().trim();
                        text = null;
                        break;
                    default:
                        name = null;
                        text = null;
                        break;
                }
                eventType = xmlParser.next();
                Log.d(TAG, "parseData_" + eventType + "_" + name + "_" + text);
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return rss;
    }

    private RockRss parseChannel(XmlPullParser parser, RockRss rss) {
        int eventType;
        String name = null;
        if (rss.itemList == null) {
            rss.itemList = new LinkedList<>();
        } else {
            rss.itemList.clear();
        }

        try {
            do {
                eventType = parser.getEventType();
                if (eventType == XmlPullParser.START_TAG) {
                    name = parser.getName();
                    if (name.equalsIgnoreCase("item")) {
                        Log.d(TAG, "parseChannel()_" + name);

                        Article rssItem = parseItem(parser, new Article());
                        if (rssItem != null) {
                            rss.itemList.add(rssItem);
                        }
                    } else {
                        setChannel(name, parser, rss);
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    name = parser.getName().trim();
                    if (name.equalsIgnoreCase("channel")) {
                        break;
                    }
                }

                parser.next();
            } while (eventType != XmlPullParser.END_DOCUMENT);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return rss;
    }

    private void setChannel(String name, XmlPullParser parser, RockRss rss) {
        try {
            switch (name) {
                case "title":
                    rss.channelTitle = parser.nextText().trim();
                    break;
                case "link":
                    rss.channelLink = parser.nextText().trim();
                    break;
                case "description":
                    rss.channelDes = parser.nextText().trim();
                    break;
                case "pubDate":
                    rss.channelPubDate = parser.nextText().trim();
                    break;
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    public Article parseItem(XmlPullParser parser, Article item) {
        int eventType;
        String name = null;
        try {
            do {
                eventType = parser.getEventType();
                if (eventType == XmlPullParser.START_TAG) {
                    name = parser.getName().trim();
                    setItem(name, parser, item);
                } else if (eventType == XmlPullParser.END_TAG) {
                    name = parser.getName().trim();
                    if (name.equalsIgnoreCase("item")) {
                        break;
                    }
                }
                Log.d(TAG, "parseItem()_" + name + "_" + eventType);
                parser.next();
            } while (eventType != XmlPullParser.END_DOCUMENT);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return item;
    }

    private void setItem(String name, XmlPullParser parser, Article item) {
        try {
            Log.d(TAG, name + "");

            switch (name) {
                case "title":
                    item.title = parser.nextText().trim();
                    break;
                case "link":

                    item.link = parser.nextText().trim();
                    break;
                case "category":
                    item.category = parser.nextText().trim();
                    break;
                case "description":
                    item.des = parser.nextText().trim();
                    break;
                case "pubDate":
                    item.pubDate = parser.nextText().trim();
                    break;
                case "guid":
                    item.guid = parser.nextText().trim();
                    break;
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
}
