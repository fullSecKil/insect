package com.gecc.tools;

/**
 * @file: DownloadFile.class
 * @author: Dusk
 * @since: 2018/12/9 19:19
 * @desc:
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 用来download
 */
public class DownloadFile {

    private String url;
    private String fileName;
    private String filePath;

    public DownloadFile(String url, String fileName, String filePath) {
        this.url = url;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public int downloadStart() throws IOException {
        int result = 0;
        URL url = new URL(this.url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setConnectTimeout(3*1000);

        //防止屏蔽程序抓取而返回403错误
        connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = connection.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        File saveDir = new File(filePath);
        if (!saveDir.exists()){
            saveDir.mkdirs();
        }

        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
            result = 1;
        }
        if(inputStream!=null){
            inputStream.close();
        }
        return result;
    }

    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        // buffer.clone();
        return bos.toByteArray();
    }
}
