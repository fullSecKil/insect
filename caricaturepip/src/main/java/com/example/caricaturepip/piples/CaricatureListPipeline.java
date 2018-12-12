package com.example.caricaturepip.piples;

import com.example.caricaturepip.gecco.CaricatureList;
import com.example.caricaturepip.service.DownloadFileNIO;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import lombok.Getter;
import lombok.Setter;
import org.apache.tools.ant.util.ReaderInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.compile;

/**
 * @file: CaricatureListPipeline.class
 * @author: Dusk
 * @since: 2018/12/10 22:40
 * @desc:
 */

/**
 * 将所有图片的路径取出来再写入本地
 * @author Dusk
 */

@Service
@PipelineName(value = "caricatureListPipeline")
public class CaricatureListPipeline implements Pipeline<CaricatureList> {

    @Value("${piples.caricature.path}")
    private String FILE_PATH;

    @Override
    public void process(CaricatureList bean) {
        List<String> arrPagesAddress = bean.getArrPages();
        String img_prefix = "http://img.dmzj.com"+"/";
        Pattern pattern = compile("[^/]+$");
        /**
         * 取出最后/前当名字
         */
        Map<String, String> arrPagesAddressMap = arrPagesAddress.stream().map(d->img_prefix+d).filter(d->pattern.matcher(d).find()).collect(Collectors.toMap(d -> d.substring(d.lastIndexOf("/")+1), Function.identity()));

        System.out.println(arrPagesAddressMap);

        String filePath = "D:\\Caricature\\";
        System.out.println("11"+filePath+bean.getCaricatureName() + "\\" + bean.getCaricatureName()+".data");
        System.out.println("22"+FILE_PATH+bean.getCaricatureName() + "\\" + bean.getCaricatureName()+".data");

        List<Integer> result = arrPagesAddressMap.entrySet().parallelStream().map(ad-> new DownloadFileNIO(ad.getValue(), ad.getKey(),  filePath + bean.getCaricatureName() + "\\" + bean.getThisChapter()).downloadStart()).collect(Collectors.toList());

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
