package com.gecc.pipelies;

import com.gecc.pojo.Blog;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;

/**
 * @file: BlogPipelines.class
 * @author: Dusk
 * @since: 2018/12/7 1:07
 * @desc:
 */

@PipelineName(value="blogPipelines")
public class BlogPipelines implements Pipeline<Blog> {
    public void process(Blog blog) {
        System.out.println(blog.getContent());
    }
}
