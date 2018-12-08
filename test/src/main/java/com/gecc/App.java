package com.gecc;

import com.geccocrawler.gecco.GeccoEngine;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        // "http://www.cnblogs.com/boychen/p/7226831.html", "https://github.com/xtuhcy/gecco"
        GeccoEngine.create()
                .classpath("com.gecc")
                .start("https://www.jd.com/allSort.aspx", "https://list.jd.com/list.html?cat=9987,653,659&delivery=1&page=1&JL=4_10_0&go=0")
                //开启几个爬虫线程
                .thread(10)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(5)
                //使用pc端userAgent
                .mobile(false)
                //开始运行
                .run();

    }
}
