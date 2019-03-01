package com.caricature;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.DynamicGecco;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @file: Application.class
 * @author: Dusk
 * @since: 2019/2/28 20:36
 * @desc:
 */
public class Application {
    public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(500);

    public static void main(String[] args) {

        List<String> caricatureList = new ArrayList<>(10);
        // city_storm++
        File file = new File("cat.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                caricatureList.add(tempString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 漫画详情页面（说明，levelList存在）

        Class<?> caricature = DynamicGecco.html()
                .listField("cateCata",
                                DynamicGecco.html()
                                .stringField("url").csspath("a").href().build()
                                .stringField("level").csspath("a").text().build()
                                .register()).csspath("li").build()
                .register();

        DynamicGecco.html()
                .gecco("https://www.manhuadb.com/manhua/{division}", "consolePipeline" ,"catalogJsonPipeline")
                .requestField("request").request().build()
                .listField("cata", caricature)
                    .csspath(".links-of-books").build().register();

        // level 的操作

        Class<?> pageSurvey = DynamicGecco.html()
                .listField("pageList",
                                    DynamicGecco.html()
                                    .stringField("path").csspath("option").attr("value").build()
                                    .stringField("title").csspath("option").build()
                                    .register()).csspath("option").build()

                .register();
// [^_p{1}]
        // [^p]* 匹配非p
        // ([^p+][_p]{1}.*)
        // [^p]+p.* 匹配p
        DynamicGecco.html()
                .gecco("https://www.manhuadb.com/manhua/{level}/[^p]*.html", "consolePipeline", "pagePathJsonPipeline")
                .requestField("request").request().build()
                .listField("caricatureCollect", pageSurvey)
                    .csspath("#page-selector").build().register();

        // page 的操作
        DynamicGecco.html()
                .gecco("https://www.manhuadb.com/manhua/{level}/[^p]+p.*.html", "consolePipeline", "pageDownloadJsonPipeline")
                .requestField("request").request().build()
                .stringField("caricatureName").csspath(".container-fluid > h1 > a").text().build()
                .stringField("cata").csspath(".breadcrumb > li.breadcrumb-item.active > a").text().build()
                .stringField("image").csspath("#all > div > div.text-center > img").attr("src").build()
                .register();


        // HttpGetRequest start = new HttpGetRequest("https://www.manhuadb.com/manhua/951");
        // start.setCharset("GBK");

        // 单线程启动
//        GeccoEngine.create()
//                .classpath("com.caricature.piples")
//                .start(caricatureList.toArray(new String[caricatureList.size()]))
//                .thread(100)
//                .interval(5)
//                .mobile(false)
//                .run();

//        for(String ca: caricatureList.toArray(new String[caricatureList.size()])){
//            fixedThreadPool.execute(()-> {
//                System.out.println(ca);
//                GeccoEngine.create()
//                        .classpath("com.caricature.piples")
//                        .start(ca)
//                        .thread(50)
//                        .interval(5)
//                        .mobile(false)
//                        .run();
//            });
//        }

        caricatureList.parallelStream().forEach(c-> GeccoEngine.create()
            .classpath("com.caricature.piples")
            .start(c)
            .thread(60)
            .interval(5)
            .mobile(false)
            .run());
    }

}
