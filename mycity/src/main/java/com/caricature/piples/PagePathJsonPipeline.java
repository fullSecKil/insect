package com.caricature.piples;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.StartSchedulerContext;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @file: PagePathJsonPipeline.class
 * @author: Dusk
 * @since: 2019/3/1 11:56
 * @desc:
 */

@PipelineName("pagePathJsonPipeline")
public class PagePathJsonPipeline extends JsonPipeline {

    @Override
    public void process(JSONObject jo) {
        HttpRequest currRequest = HttpGetRequest.fromJson(jo.getJSONObject("request"));
// 注释x掉
//        JSONArray caricatureCollectGorys = jo.getJSONArray("caricatureCollect");
//        process(currRequest, caricatureCollectGorys);
        String pageNumber = jo.getString("pageNumber");
        String pages = StringUtils.substringBetween(pageNumber, "共", "页").trim();
        if (StringUtils.isNumeric(pages)) {
            processPages(currRequest, Integer.parseInt(pages));
        }

    }

    private void processPages(HttpRequest currRequest, int parseInt) {
        final String url = currRequest.getUrl();
        IntStream.rangeClosed(1, parseInt).mapToObj(i -> StringUtils.replace(url, ".html", String.format("_p%d.html", i))).forEach(
                path -> StartSchedulerContext.into(currRequest.subRequest(path))
        );
    }

    /**
     * 处理 option list 方法 网站变化已废
     *
     * @param currRequest
     * @param caricatureCollectGorys
     */
    private void process(HttpRequest currRequest, JSONArray caricatureCollectGorys) {

        String imgPrefix = "https://www.manhuadb.com";
        Optional.ofNullable(caricatureCollectGorys).ifPresent(c -> c.parallelStream().map(c1 -> ((JSONObject) c1).getJSONArray("pageList"))
                .flatMap(c1 -> c1.stream().map((c3) -> ((JSONObject) c3).getString("path")))
                // .forEach(path-> System.out.println(MessageFormat.format("{0}{1}", "https://www.manhuadb.com", path))));
                .forEach(path -> StartSchedulerContext.into(currRequest.subRequest(MessageFormat.format("{0}{1}", imgPrefix, path)))));
    }
}
