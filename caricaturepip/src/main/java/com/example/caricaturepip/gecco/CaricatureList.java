package com.example.caricaturepip.gecco;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.JSVar;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * @file: CaricatureList.class
 * @author: Dusk
 * @since: 2018/12/10 22:37
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
