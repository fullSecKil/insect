package com.gecc.pojo;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;
import org.apache.http.HttpRequest;

import java.util.List;
import java.util.Map;

/**
 * @file: CaricatureList.class
 * @author: Dusk
 * @since: 2018/12/9 12:16
 * @desc:
 */

@Gecco(matchUrl = "https://manhua.dmzj.com/{name}/{count}.shtml", pipelines = {"consolePipeline", "caricatureListPipeline"})
public class CaricatureList implements HtmlBean {

    private static final long serialVersionUID = -4369792078959596706L;

    /**
     * 漫画和第几页的
     */

    @JSVar(var = "arr_pages", jsonpath = "$[0:]")
    private List<String> arrPages;

    /**
     * 名字
     */
   @Text
    @HtmlField(cssPath = ".display_graybg > div.display_middle > h1 > a")
    private String caricatureName;

    /**
     * 此第多少话
     */
    @Text
    @HtmlField(cssPath = ".display_graybg > div.display_middle > span")
    private String thisChapter;

    public List<String> getArrPages() {
        return arrPages;
    }

    public void setArrPages(List<String> arrPages) {
        this.arrPages = arrPages;
    }

    public String getCaricatureName() {
        return caricatureName;
    }

    public void setCaricatureName(String caricatureName) {
        this.caricatureName = caricatureName;
    }

    public String getThisChapter() {
        return thisChapter;
    }

    public void setThisChapter(String thisChapter) {
        this.thisChapter = thisChapter;
    }
}
