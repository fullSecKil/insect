package com.gecc.piples;

import com.gecc.pojo.CaricatureList;
import com.gecc.tools.DownloadFile;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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

        String filePath = "D:\\Caricature\\";

        List<Integer> result= arrPagesAddressMap.entrySet().parallelStream().map(ad-> {
            try {
                // return new DownloadFile(ad.getValue(), ad.getKey(),  "/usr/caricature/" + bean.getCaricatureName() + "/" + bean.getThisChapter()).downloadStart();
                return new DownloadFile(ad.getValue(), ad.getKey(),   filePath + bean.getCaricatureName() + "\\" + bean.getThisChapter()).downloadStart();
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }).collect(toList());

        System.out.println(result);

        Map<String ,Map<String, String>> fileText = new HashMap<>();
        fileText.put(bean.getThisChapter(), arrPagesAddressMap);
        File file = new File(filePath + bean.getCaricatureName() + "\\" + bean.getCaricatureName()+".data");

        // 追加
        try (FileOutputStream fop = new FileOutputStream(file, true)){
            if (! file.exists()){
                file.createNewFile();
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fop);
            objectOutputStream.writeObject(fileText);
            objectOutputStream.flush();
            fop.close();
            objectOutputStream.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
