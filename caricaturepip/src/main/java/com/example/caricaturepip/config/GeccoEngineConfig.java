package com.example.caricaturepip.config;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.geccocrawler.gecco.spring.SpringGeccoEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;

/**
 * @file: GeccoEngineConfig.class
 * @author: Dusk
 * @since: 2018/12/11 22:12
 * @desc:
 */

@Configuration
public class GeccoEngineConfig {

/*    @Lazy
    @Bean
    public SpringGeccoEngine initGecco(){
        return new SpringGeccoEngine() {
            @Override
            public void init() {
                GeccoEngine.create()
                        .pipelineFactory(springPipelineFactory)
                        .classpath("com.example.caricaturepip.gecco")
                        .start("https://manhua.dmzj.com/xfgj", "https://manhua.dmzj.com/mofajinshumulu/")
                        .interval(3000)
                        .loop(true)
                        .start();
            }
        };
    }*/
/*
    @Lazy
    @Bean
    public AbstractGeccoConfig abstractGeccoConfig(){
        return new AbstractGeccoConfig(){
            @Override
            public void init(String... urls) {
                GeccoEngine.create()
                        .pipelineFactory(springPipelineFactory)
                        .classpath("com.example.caricaturepip.gecco")
                        .start(urls)
                        .interval(3000)
                        .loop(true)
                        .start();
            }
        };
    }
*/

}
