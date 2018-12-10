package com.gecc;
import com.geccocrawler.gecco.GeccoEngine;


/**
 * @file: App.class
 * @author: Dusk
 * @since: 2018/12/9 0:07
 * @desc:
 */
public class App {
    public static void main(String[] args) {
        GeccoEngine.create()
                .classpath("com.gecc")
                // "http://www.cnblogs.com/boychen/p/7226831.html", , "https://manhua.dmzj.com/baisexiangbu2"
                .start("https://manhua.dmzj.com/xfgj", "https://manhua.dmzj.com/mofajinshumulu/")
                //开启几个爬虫线程
                .thread(100)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(5)
                //使用pc端userAgent
                .mobile(false)
                //开始运行
                .run();
    }
}
