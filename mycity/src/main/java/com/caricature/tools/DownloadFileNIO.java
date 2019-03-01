package com.caricature.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * @file: DownloadFileNIO.class
 * @author: Dusk
 * @since: 2019/3/1 15:11
 * @desc:
 */

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
        return result;
    }
}
