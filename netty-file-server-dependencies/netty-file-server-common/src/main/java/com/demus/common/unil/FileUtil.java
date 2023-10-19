package com.demus.common.unil;

import com.demus.common.message.SimpleFileMessage;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

    public static void saveFile(SimpleFileMessage simpleFileMessage, String pathname) throws IOException {

//        byte[] array = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(array);
        byte[] fileBytes = simpleFileMessage.getFileBytes();

        File outputFile = new File(pathname);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        fileOutputStream.write(fileBytes);

    }

    public static SimpleFileMessage getFileBO(String pathname) throws IOException {
        File file = new File(pathname);
        InputStream inputStream = new FileInputStream(file);

        byte[] bytes = toBytes(inputStream);
        return new SimpleFileMessage(bytes.length, bytes);
    }
}
