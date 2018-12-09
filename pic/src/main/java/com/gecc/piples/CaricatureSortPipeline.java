package com.gecc.piples;

import com.gecc.pojo.Caricature;
import com.gecc.pojo.CaricatureSort;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.StartSchedulerContext;
import com.geccocrawler.gecco.spider.HrefBean;

import java.util.List;

/**
 * @file: CaricatureSortPipeline.class
 * @author: Dusk
 * @since: 2018/12/9 0:43
 * @desc:
 */

@PipelineName(value = "caricatureSortPipeline")
public class CaricatureSortPipeline implements Pipeline<CaricatureSort> {
    @Override
    public void process(CaricatureSort bean) {
        List<Caricature> caricatureSortList = bean.getFist();
        for (Caricature caricature : caricatureSortList){
            List<HrefBean> hrefBeanList = caricature.getCategorys();
            for (HrefBean hrefBean :  hrefBeanList){
                String url = hrefBean.getUrl();
                HttpRequest currRequest =  bean.getRequest();
                StartSchedulerContext.into(currRequest.subRequest(url));
            }
        }
    }
}
