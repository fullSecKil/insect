package com.gecc.pojo;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.SpiderBean;

/**
 * @file: Blog.class
 * @author: Dusk
 * @since: 2018/12/7 1:06
 * @desc:
 */

@Gecco(matchUrl="http://www.cnblogs.com/boychen/p/7226831.html",pipelines="blogPipelines")
public class Blog implements SpiderBean {
    /**
     * 向指定URL发送GET方法的请求
     */
    @Request
    private HttpRequest request;

    /**
     * 抓去这个路径下所有的内容
     */
    @HtmlField(cssPath = "body div#cnblogs_post_body")
    private String content;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
