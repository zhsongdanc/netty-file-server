package com.demus.load.server.unil;

import io.netty.buffer.ByteBuf;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/10/19 13:16
 */
public class FileUtil {

    public static byte[] toBytes(InputStream inputStream) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int i;
        byte[] buffer = new byte[4096];
        while ((i = inputStream.read()) != -1) {
            out.write(buffer, 0 ,i);
        }

        return out.toByteArray();
    }

    public static void saveFile(ByteBuf byteBuf, String pathname) throws IOException {

        byte[] array = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(array);

        File outputFile = new File(pathname);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        fileOutputStream.write(array);

    }
}
