package com.example.caricaturepip.config;

import com.geccocrawler.gecco.pipeline.PipelineFactory;

import javax.annotation.Resource;

/**
 * @file: AbstractGeccoConfig.class
 * @author: Dusk
 * @since: 2018/12/11 23:52
 * @desc:
 */
public abstract class AbstractGeccoConfig {

    @Resource
    protected PipelineFactory springPipelineFactory;
    /**
     * spring下启动gecco的初始化方法，spring下必须要用非阻塞方式start()运行GeccoEngine
     */
    public abstract void init(String... url);
}
