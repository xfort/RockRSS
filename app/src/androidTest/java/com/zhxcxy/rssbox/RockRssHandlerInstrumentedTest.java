package com.zhxcxy.rssbox;

import com.zhxcxy.rssbox.bean.RockRss;
import com.zhxcxy.rssbox.bean.RockRssHandler;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhxcxy on 16-11-2.
 */
public class RockRssHandlerInstrumentedTest {
    String rssStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<rss version=\"2.0\">\n" +
            "<channel>\n" +
            "    <title>开源中国社区最新新闻</title> \n" +
            "    <link>http://www.oschina.net/?from=rss</link> \n" +
            "    <description>开源中国社区——找到您想要的开源软件，分享和交流</description>\n" +
            "    <language>zh-CN</language>\n" +
            "    <pubDate>Wed, 02 Nov 2016 17:47:25 +0800</pubDate>    \n" +
            "\t<image>\n" +
            "\t\t<link>http://www.oschina.net</link>\n" +
            "\t\t<url>http://www.oschina.net/img/logo.gif</url>\n" +
            "\t\t<title>OsChina.NET</title>\n" +
            "\t</image>\n" +
            "\t<item>\n" +
            "        <title>Zotero 4.0.29.16 小版本更新，Firefox 扩展工具</title>\n" +
            "        <link>https://www.oschina.net/news/78651/zotero-4-0-29-16</link>\n" +
            "        <category>软件更新新闻</category>\n" +
            "        <description><![CDATA[<p>Zotero 4.0.29.16 发布了，Zotero是一个免费易用的Firefox扩展。Zotero 可以协助我们收集、管理及引用研究资源，包括期刊、书籍等各类文献和网页、图片等。</p><p>此次更新主要是修复bug:</p><ul class=\" list-paddingleft-2\"><li><p>Miscellaneous bibliographic output and word processor integration fixes</p></li><ul class=\" list-paddingleft-2\" style=\"list-style-type: square;\"><li><p>Updated citeproc-js to version 1.1.138</p></li></ul></ul>]]></description>\n" +
            "        <pubDate>Wed, 02 Nov 2016 17:47:25 +0800</pubDate>\n" +
            "        <guid>https://www.oschina.net/news/78651/zotero-4-0-29-16</guid>\n" +
            "    </item>\n" +
            "\t<item>\n" +
            "        <title>RStudio v1.0.44 发布，首个1.0版本</title>\n" +
            "        <link>https://www.oschina.net/news/78650/rstudio-v-1-0-44</link>\n" +
            "        <category>软件更新新闻</category>\n" +
            "        <description><![CDATA[<p>RStudio v1.0.44 版本发布了，RStudio是R语言的集成开发环境，分为面向桌面用户 IDE 和 Linux R 服务器版编辑器两种编辑器，采用AGPL v3 与RStudio License Agreement 双协议授权。<br/></p><p>部分更新内容：</p><p><strong>Highlights</strong></p><ol class=\" list-paddingleft-2\"><li><p>Authoring tools for&nbsp;<a data-cke-saved-href=\"http://rmarkdown.rstudio.com/r_notebooks.html\" href=\"http://rmarkdown.rstudio.com/r_notebooks.html\">R Notebooks</a>.</p></li><li><p>Integrated support for the&nbsp;<a data-cke-saved-href=\"http://spark.rstudio.com/\" href=\"http://spark.rstudio.com/\">sparklyr</a>&nbsp;package (R interface to Spark).</p></li><li><p>Enhanced data import tools based on the&nbsp;<a data-cke-saved-href=\"https://github.com/hadley/readr\" href=\"https://github.com/hadley/readr\">readr</a>,&nbsp;<a data-cke-saved-href=\"https://github.com/hadley/readxl\" href=\"https://github.com/hadley/readxl\">readxl</a>&nbsp;and&nbsp;<a data-cke-saved-href=\"https://github.com/hadley/haven\" href=\"https://github.com/hadley/haven\">haven</a>&nbsp;packages.</p></li><li><p>Performance profiling via integration with the&nbsp;<a data-cke-saved-href=\"https://github.com/rstudio/profvis\" href=\"https://github.com/rstudio/profvis\">profvis</a>&nbsp;package.</p></li><li><p>Authoring tools for R Markdown&nbsp;<a data-cke-saved-href=\"http://rmarkdown.rstudio.com/rmarkdown_websites.html\" href=\"http://rmarkdown.rstudio.com/rmarkdown_websites.html\">websites</a>&nbsp;and the&nbsp;<a data-cke-saved-href=\"https://bookdown.org/\" href=\"https://bookdown.org/\">bookdown</a>&nbsp;package.</p></li><li><p>Many other miscellaneous enhancements and bug fixes.</p></li></ol><p><strong>R Notebooks</strong></p><ol class=\" list-paddingleft-2\"><li><p>Authoring tools for&nbsp;<a data-cke-saved-href=\"http://rmarkdown.rstudio.com/r_notebooks.html\" href=\"http://rmarkdown.rstudio.com/r_notebooks.html\">R Notebooks</a></p></li><li><p>Inline display for text, latex, tabular data, graphics, and htmlwidgets in source editor</p></li><li><p>All code and output saved within a single notebook HTML file (.nb.html)</p></li><li><p>Multiple language engines including Python, Bash, SQL, Rcpp, and Stan</p></li><li><p>Tools for running various combinations of chunks (current, next, previous, remaining)</p></li></ol><p>更多请查看完整<a data-cke-saved-href=\"https://www.rstudio.com/products/rstudio/release-notes/\" target=\"_blank\" href=\"https://www.rstudio.com/products/rstudio/release-notes/\">更新列表</a></p>]]></description>\n" +
            "        <pubDate>Wed, 02 Nov 2016 13:49:33 +0800</pubDate>\n" +
            "        <guid>https://www.oschina.net/news/78650/rstudio-v-1-0-44</guid>\n" +
            "    </item>\n" +
            "</channel>\n" +
            "</rss>";
    RockRssHandler rssHandler;

    @Before
    public void setUp() throws Exception {
        rssHandler = new RockRssHandler();

    }

    @Test
    public void parseData() throws Exception {
        RockRss rockRss = rssHandler.parseData(rssStr, null);
        assertEquals(rockRss, null);
    }

}