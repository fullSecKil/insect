package com.gecc.piples;

import com.gecc.pojo.CaricatureList;
import com.gecc.tools.DownloadFile;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.*;
import static java.util.stream.Collectors.toList;

/**
 * @file: CaricatureListPipeline.class
 * @author: Dusk
 * @since: 2018/12/9 14:53
 * @desc:
 */

/**
 * 将所有图片的路径取出来再写入本地
 * @author Dusk
 */
@PipelineName(value = "caricatureListPipeline")
public class CaricatureListPipeline implements Pipeline<CaricatureList> {
    @Override
    public void process(CaricatureList bean) {
        List<String> arrPagesAddress = bean.getArrPages();
        String img_prefix = "http://img.dmzj.com"+"/";
        Pattern pattern = compile("[^/]+$");
        /**
         * 取出最后/前当名字
         */
        Map<String, String> arrPagesAddressMap=  arrPagesAddress.stream().map(d->img_prefix+d).filter(d->pattern.matcher(d).find()).collect(Collectors.toMap(d -> d.substring(d.lastIndexOf("/")+1), Function.identity()));

        System.out.println(arrPagesAddressMap);

        List<Integer> result= arrPagesAddressMap.entrySet().parallelStream().map(ad-> {
            try {
                return new DownloadFile(ad.getValue(), ad.getKey(),  "/usr/caricature/" + bean.getCaricatureName() + "/" + bean.getThisChapter()).downloadStart();
                // return new DownloadFile(ad.getValue(), ad.getKey(),  "E:\\Caricature\\" + bean.getCaricatureName() + "\\" + bean.getThisChapter()).downloadStart();
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }).collect(toList());

        System.out.println(result);
    }
}
