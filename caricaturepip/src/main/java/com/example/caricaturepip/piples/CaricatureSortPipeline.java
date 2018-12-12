package com.example.caricaturepip.piples;

import com.example.caricaturepip.gecco.CaricatureSort;
import com.example.caricaturepip.pojo.Caricature;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.StartSchedulerContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @file: CaricatureSortPipeline.class
 * @author: Dusk
 * @since: 2018/12/10 21:22
 * @desc:
 */
@Service
@PipelineName(value = "caricatureSortPipeline")
public class CaricatureSortPipeline implements Pipeline<CaricatureSort> {
    @Override
    public void process(CaricatureSort bean) {
        List<Caricature> caricatureSortList = bean.getFist();
        HttpRequest currRequest =  bean.getRequest();
        caricatureSortList.stream().map(Caricature::getCategorys).flatMap(c->c.parallelStream()).forEach(hrefBean -> StartSchedulerContext.into(currRequest.subRequest(hrefBean.getUrl())));
    }
}
