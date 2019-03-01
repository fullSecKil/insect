package com.caricature.piples;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.StartSchedulerContext;

import java.text.MessageFormat;
import java.util.Optional;

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
        JSONArray caricatureCollectGorys = jo.getJSONArray("caricatureCollect");
        process(currRequest, caricatureCollectGorys);
    }

    private void process(HttpRequest currRequest, JSONArray caricatureCollectGorys){

        String imgPrefix ="https://www.manhuadb.com";
        Optional.ofNullable(caricatureCollectGorys).ifPresent(c->c.parallelStream().map(c1->((JSONObject)c1).getJSONArray("pageList"))
        .flatMap(c1->c1.stream().map((c3)->((JSONObject)c3).getString("path")))
        // .forEach(path-> System.out.println(MessageFormat.format("{0}{1}", "https://www.manhuadb.com", path))));
        .forEach(path->StartSchedulerContext.into(currRequest.subRequest(MessageFormat.format("{0}{1}", imgPrefix, path)))));
    }
}
