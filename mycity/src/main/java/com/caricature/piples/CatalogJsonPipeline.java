package com.caricature.piples;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.StartSchedulerContext;

/**
 * @file: CatalogJsonPipeline.class
 * @author: Dusk
 * @since: 2019/2/28 22:41
 * @desc:
 */

@PipelineName("catalogJsonPipeline")
public class CatalogJsonPipeline extends JsonPipeline {

    @Override
    public void process(JSONObject jo) {
        HttpRequest currRequest = HttpGetRequest.fromJson(jo.getJSONObject("request"));
        JSONArray categorys = jo.getJSONArray("cata");
        process(currRequest, categorys);
    }

    private void process(HttpRequest currRequest, JSONArray categorys){
        if(categorys == null){
            return;
        }
        // StartSchedulerContext.into(currRequest.subRequest("https://www.manhuadb.com/manhua/951/985_9891.html"));
        categorys.parallelStream().map(c->((JSONObject) c).getJSONArray("cateCata")).flatMap(c->c.stream().map(c1->((JSONObject)c1).getString("url"))).forEach(url->StartSchedulerContext.into(currRequest.subRequest(url)));
        // c.stream().map(c.getString("url"))
    }
}
