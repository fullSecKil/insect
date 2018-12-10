package com.gecc.tools;

import java.io.*;
import java.net.URL;

public class DownloadFileNIO {

    private String url;
    private String fileName;
    private String filePath;

    public DownloadFileNIO(String url, String fileName, String filePath) {
        this.url = url;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public int downloadStart() {
        int result = 0;
        File saveDir = new File(filePath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        File file = new File(saveDir + File.separator + fileName);

        try (BufferedInputStream in = new BufferedInputStream(new URL(this.url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            result = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

// http://www.spring4all.com/article/1782