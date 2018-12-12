package com.example.caricaturepip.gecco;

import com.example.caricaturepip.pojo.Caricature;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * @file: CaricatureSort.class
 * @author: Dusk
 * @since: 2018/12/10 21:15
 * @desc:
 */

@Gecco(matchUrl = "https://manhua.dmzj.com/{name}", pipelines = {"consolePipeline", "caricatureSortPipeline"})
public class CaricatureSort implements HtmlBean {

    private static final long serialVersionUID = 665662335318691818L;

    @Request
    private HttpRequest request;

    // 一拳超人
    @HtmlField(cssPath = ".middleright > div:nth-child(1) > div.cartoon_online_border > ul")
    private List<Caricature> fist;

    @Text
    @HtmlField(cssPath = ".middleright > div:nth-child(1) > div.odd_anim_title > div.odd_anim_title_m > span.anim_title_text > a > h1")
    private String caricatureName;

    @Text
    @HtmlField(cssPath = ".middleright > div:nth-child(1) > div.odd_anim_title > div.odd_anim_title_m > a")
    private String newChapter;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public List<Caricature> getFist() {
        return fist;
    }

    public void setFist(List<Caricature> fist) {
        this.fist = fist;
    }

    public String getCaricatureName() {
        return caricatureName;
    }

    public void setCaricatureName(String caricatureName) {
        this.caricatureName = caricatureName;
    }

    public String getNewChapter() {
        return newChapter;
    }

    public void setNewChapter(String newChapter) {
        this.newChapter = newChapter;
    }
}
