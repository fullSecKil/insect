package com.gecc.piples;

import com.gecc.pojo.Blog;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;

/**
 * @file: BlogPipelies.class
 * @author: Dusk
 * @since: 2018/12/9 0:11
 * @desc:
 */

@PipelineName(value = "blogPipelines")
public class BlogPipelies implements Pipeline<Blog> {
    @Override
    public void process(Blog bean) {
        // System.out.println(bean.getRequest());
    }
}
