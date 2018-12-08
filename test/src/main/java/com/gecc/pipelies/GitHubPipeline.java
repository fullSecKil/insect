package com.gecc.pipelies;

import com.gecc.pojo.MyGithub;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.spider.SpiderBean;

@PipelineName(value="gitHubPipeline")
public class GitHubPipeline implements Pipeline<MyGithub> {

    @Override
    public void process(MyGithub myGithub) {
        System.out.println(myGithub);
    }
}
