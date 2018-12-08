package com.gecc.pipelies;

import com.gecc.pojo.ProductList;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@PipelineName("productListPipeline")
public class ProductListPipeline implements Pipeline<ProductList> {

    @Override
    public void process(ProductList productList) {

        HttpRequest currRequest = productList.getRequest();
        // 下一页抓取
        int currPage = productList.getCurrPage();
        System.out.println(currPage);
        System.out.println(productList.getTotalPage());
        int nextPage = currPage+1;
        int totalPage = productList.getTotalPage();
        if(nextPage <= totalPage) {
            String nextUrl = "";
            String currUrl = currRequest.getUrl();
            if(currUrl.indexOf("page=") != -1){
                nextUrl = StringUtils.replaceOnce(currUrl, "page=" + currPage, "page=" + nextPage);
            } else {
                nextUrl = currUrl + "&" +"page=" + nextPage;
            }
            // DeriveSchedulerContext.into(currRequest.subRequest(nextUrl));
            StartSchedulerContext.into(currRequest.subRequest(nextUrl));
        }

    }
}
