package com.gecc;
import com.geccocrawler.gecco.GeccoEngine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @file: App.class
 * @author: Dusk
 * @since: 2018/12/9 0:07
 * @desc:
 */
public class App {
    public static void main(String[] args) {
        List<String> caricatureList = new ArrayList<>(10);
        File file = new File("cat.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                caricatureList.add(tempString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        GeccoEngine.create()
                .classpath("com.gecc")
                // "http://www.cnblogs.com/boychen/p/7226831.html", , "https://manhua.dmzj.com/baisexiangbu2"
                // .start("https://manhua.dmzj.com/xfgj", "https://manhua.dmzj.com/mofajinshumulu/")
                .start(caricatureList.toArray(new String[caricatureList.size()]))
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
