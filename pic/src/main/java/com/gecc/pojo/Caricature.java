package com.gecc.pojo;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * @file: Caricature.class
 * @author: Dusk
 * @since: 2018/12/9 0:31
 * @desc:
 */
public class Caricature implements HtmlBean {
    private static final long serialVersionUID = 3018760488621382659L;

    @Text
    @HtmlField(cssPath = "li a")
    private String parentName;

    @HtmlField(cssPath = "li a")
    private List<HrefBean> categorys;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<HrefBean> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<HrefBean> categorys) {
        this.categorys = categorys;
    }
}
