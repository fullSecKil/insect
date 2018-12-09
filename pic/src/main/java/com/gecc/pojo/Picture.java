package com.gecc.pojo;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * @file: Picture.class
 * @author: Dusk
 * @since: 2018/12/9 13:51
 * @desc:
 */
public class Picture implements HtmlBean {

    private static final long serialVersionUID = -377053120283382723L;

/*    @Text
    @HtmlField(cssPath = "option")*/
    private String parentName="1233";
/*
    @Image({"data-lazy-img", "src"})
    @HtmlField(cssPath="option > value")
    private String preview;*/

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
/*
    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }*/
}
