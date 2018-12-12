package com.example.caricaturepip.controller;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.spring.SpringGeccoEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @file: MainController.class
 * @author: Dusk
 * @since: 2018/12/10 21:11
 * @desc:
 */
@RestController
public class MainController {

    @Value("${piples.caricature.path}")
    private String FILE_PATH;

    @GetMapping("/index")
    public String index(){
        System.out.println(FILE_PATH);
        // springGeccoEngine.init();

        GeccoEngine.create()
                .classpath("com.example.caricaturepip")
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

        return "xuerui";
    }

}
