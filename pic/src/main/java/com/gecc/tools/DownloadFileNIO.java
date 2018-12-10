package com.gecc.tools;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

        //NIO
        try {
            ReadableByteChannel readableChannel = Channels.newChannel(new URL(this.url).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
            FileChannel fileChannel = fileOutputStream.getChannel();
            fileOutputStream.getChannel().transferFrom(readableChannel, 0, Long.MAX_VALUE);
            result = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        try {
            InputStream in = new URL(this.url).openStream();
            Files.copy(in, Paths.get(file.getPath()), StandardCopyOption.REPLACE_EXISTING);
            result = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }*/

/*        try (BufferedInputStream in = new BufferedInputStream(new URL(this.url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            result = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return result;
    }
}

// http://www.spring4all.com/article/1782