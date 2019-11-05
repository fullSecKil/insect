package com.caricature.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * @author dusk
 * @since 2019/11/6 0:11
 */
public class DownloadFileNIOPlus {

    public static void createFilePath(String filePath) {
        File saveDir = new File(filePath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
    }

    public static int downloadStart(String url, String fileName, String filePath) {
        int result = 0;

//        File file = new File(saveDir + File.separator + fileName);
        File file = new File(filePath, fileName);

        //NIO
        try {
            ReadableByteChannel readableChannel = Channels.newChannel(new URL(url).openStream());
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
