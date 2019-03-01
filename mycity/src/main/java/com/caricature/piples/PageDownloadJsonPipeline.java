package com.caricature.piples;

import com.alibaba.fastjson.JSONObject;
import com.caricature.tools.DownloadFileNIO;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;

import java.io.File;
import java.text.MessageFormat;

import static com.caricature.Application.fixedThreadPool;

/**
 * @file: PageDownloadJsonPipeline.class
 * @author: Dusk
 * @since: 2019/3/1 15:19
 * @desc:
 */
@PipelineName("pageDownloadJsonPipeline")
public class PageDownloadJsonPipeline extends JsonPipeline {
    @Override
    public void process(JSONObject jo) {
        HttpRequest currRequest = HttpGetRequest.fromJson(jo.getJSONObject("request"));
        process(currRequest, jo);
    }

    public void process(HttpRequest currRequest, JSONObject caricaturePage) {
        String imgPrefix ="https://www.manhuadb.com";
        String caricaturePath = caricaturePage.getString("image");
        String imageAbsoluteUrl = MessageFormat.format("{0}{1}", imgPrefix, caricaturePath);
        String caricatureName = caricaturePage.getString("caricatureName");
        String cataLog = caricaturePage.getString("cata");

        fixedThreadPool.execute(()->{
            System.out.println(new DownloadFileNIO(imageAbsoluteUrl, imageAbsoluteUrl.substring(imageAbsoluteUrl.lastIndexOf("/")+1), caricatureName + File.separator  + cataLog).downloadStart());
        });
    }
}
