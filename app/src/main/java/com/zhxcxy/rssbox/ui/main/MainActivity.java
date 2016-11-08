package com.zhxcxy.rssbox.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zhxcxy.rssbox.R;
import com.zhxcxy.rssbox.bean.RockRss;
import com.zhxcxy.rssbox.bean.RockRssHandler;

/**
 * Created by zhxcxy on 16-11-7.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testRSS();
    }

    /**
     * 测试
     */
    private void testRSS() {
        String rssStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<rss version=\"2.0\">\n" +
                "  <channel>\n" +
                "    <title>开源中国社区最新新闻</title>\n" +
                "    <link>http://www.oschina.net/?from=rss</link>\n" +
                "    <description>开源中国社区——找到您想要的开源软件，分享和交流</description>\n" +
                "    <language>zh-CN</language>\n" +
                "    <pubDate>Mon, 07 Nov 2016 17:16:42 +0800</pubDate>\n" +
                "    <image>\n" +
                "      <link>http://www.oschina.net</link>\n" +
                "      <url>http://www.oschina.net/img/logo.gif</url>\n" +
                "      <title>OsChina.NET</title></image>\n" +
                "    <item>\n" +
                "      <title>KBEngine v0.9.5 发布，分布式游戏服务端引擎</title>\n" +
                "      <link>https://www.oschina.net/news/78794</link>\n" +
                "      <category>软件更新新闻</category>\n" +
                "      <description>\n" +
                "        <![CDATA[<p>KBEngine 是一款开源的游戏服务端引擎，客户端通过简单的约定协议就能与服务端通讯， 使用 KBEngine 插件能够快速与(Unity3D, OGRE, Cocos2d-x, HTML5, 等等)技术结合形成一个完整的客户端。</p><p>服务端底层框架使用 C++ 编写，游戏逻辑层使用 Python(支持热更新)，开发者无需重复的实现一些游戏服务端通用的底层技术， 将精力真正集中到游戏开发层面上来，快速的打造各种网络游戏。</p><p>(经常被问到承载上限，KBEngine 底层架构被设计为多进程分布式动态负载均衡方案， 理论上只需要不断扩展硬件就能够不断增加承载上限，单台机器的承载上限取决于游戏逻辑本身的复杂度。)</p><p><img src=\"http://static.oschina.net/uploads/img/201412/10095005_TYi9.jpg\"/></p><p><img src=\"http://static.oschina.net/uploads/img/201412/10094947_EbQt.jpg\"/></p><p>v0.9.5</p><p><strong> 新增与改善：</strong></p><ul class=\" list-paddingleft-2\"><li><p><strong> 数据库kbe_entitylog表新增加logger字段， 表示由哪个dbmgr记录。网络层一点小优化(#420)。</strong></p></li><li><p><strong>WebConsole增加Watcher查看功能。</strong></p></li><li><p><strong>支持使用GCC 6.2.1及以上编译器版本编译引擎(#425)。</strong></p></li><li><p><strong>更新API文档。</strong></p></li></ul><p><strong> BUG修正：</strong></p><ul class=\" list-paddingleft-2\"><li><p><strong> onMove中移动自己后销毁自己有概率crash（修正多线程下DebugHelper::onMessage中检查日志超量做清理clearBufferedLog时可能有线程竞争(#426)）。</strong></p></li><li><p><strong> controlledBy设置为None，玩家周围没有怪服务器无法移动玩家（修正Linux上第一次安装编译源码第三方库出错后就无法再次编译通过问题(#427)）</strong></p></li></ul><p>详细信息请查看：<a href=\"http://kbengine.org/\">http://kbengine.org</a></p>]]></description>\n" +
                "      <pubDate>Mon, 07 Nov 2016 17:16:42 +0800</pubDate>\n" +
                "      <guid>https://www.oschina.net/news/78794</guid></item>\n" +
                "    <item>\n" +
                "      <title>电子商务系统 DBShop V0.9.3 Beta 20161107 发布</title>\n" +
                "      <link>https://www.oschina.net/news/78793/dbshop-v0-9-3-beta-20161107</link>\n" +
                "      <category>软件更新新闻</category>\n" +
                "      <description>\n" +
                "        <![CDATA[<p>DBShop V0.9.3 Beta 2016.11.07<br/>-------------------------------------------------<br/>修正 前台会员中心 会员头像处理的代码<br/>修正 手机网页支付宝支付问题（代码老旧，进行了重新编写，可支持唤醒支付宝app功能）<br/>修正 后台手机短信设置，阿里大于 没有添加链接<br/>修正 手机网页 会员注册时，session内没有加入客户组ID<br/><br/>优化 安装程序中加入对php版本的上限限制<br/>优化 安装程序中对于soap的判断，同时加入pdo和soap开启方法的链接地址<br/>优化 手机模板中可加入统计代码<br/>优化 更好支付宝手机网页支付的图片<br/>优化 后台商品列表中加入商品分类检索<br/>优化 安装程序中加入对rewrite（重写）的判断<br/>优化 前台会员中心首页，加入 余额充值 按钮<br/>优化 默认模板中的js进行优化<br/>优化 默认模板商品详情中的 二维码 图片 适当调大<br/><br/>新增 积分类型，类型分类两种 消费积分、等级积分<br/>新增 积分商品 在后台商品编辑或添加中，可设置积分购买商品，同时后台商品列表可进行积分查询<br/>新增 前台积分商品购买<br/>新增 iis7及以上版本的重写规则文件<br/>新增 发票功能加入发票抬头输入项<br/>新增 后台会员列表中可调节会员积分<br/>新增 手机模板加入对虚拟商品、积分购物、发票信息的支持<br/></p>]]></description>\n" +
                "      <pubDate>Mon, 07 Nov 2016 17:02:21 +0800</pubDate>\n" +
                "      <guid>https://www.oschina.net/news/78793/dbshop-v0-9-3-beta-20161107</guid></item>\n" +
                "  </channel>\n" +
                "</rss>";
        RockRssHandler rockRssHandler = new RockRssHandler();
        rockRssHandler.parseData(rssStr, new RockRss());
    }
}
