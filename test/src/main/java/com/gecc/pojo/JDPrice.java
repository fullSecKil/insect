package com.gecc.pojo;

import com.geccocrawler.gecco.annotation.JSONPath;
import com.geccocrawler.gecco.spider.JsonBean;

/**
 * @file: JDPrice.class
 * @author: Dusk
 * @since: 2018/12/7 23:45
 * @desc:
 */
public class JDPrice implements JsonBean {
    private static final long serialVersionUID = -5696033709028657709L;

    @JSONPath("$.id[0]")
    private String code;

    @JSONPath("$.p[0]")
    private float price;

    @JSONPath("$.m[0]")
    private float srcPrice;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSrcPrice() {
        return srcPrice;
    }

    public void setSrcPrice(float srcPrice) {
        this.srcPrice = srcPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
