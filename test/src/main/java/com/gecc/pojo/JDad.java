package com.gecc.pojo;

import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.JSONPath;
import com.geccocrawler.gecco.spider.JsonBean;

import java.util.List;

/**
 * @file: JDad.class
 * @author: Dusk
 * @since: 2018/12/7 23:45
 * @desc:
 */
public class JDad implements JsonBean {
    private static final long serialVersionUID = 2250225801616402995L;

    @JSONPath("$.ads[0].ad")
    private String ad;

    @JSONPath("$.ads")
    private List<JSONObject> ads;

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public List<JSONObject> getAds() {
        return ads;
    }

    public void setAds(List<JSONObject> ads) {
        this.ads = ads;
    }
}
