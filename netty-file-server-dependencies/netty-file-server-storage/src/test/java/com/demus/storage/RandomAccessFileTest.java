package com.demus.storage;

import com.demus.common.constant.Constant;
import com.demus.common.constant.TestConstant;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.junit.Test;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/10/20 17:27
 */
public class RandomAccessFileTest {

    @Test
    public void sliceAndRecombination() throws IOException {
        try {
            // 打开源文件和目标文件的 RandomAccessFile 对象
            RandomAccessFile sourceFile = new RandomAccessFile(TestConstant.slicePathName, "r");
            RandomAccessFile targetFile = new RandomAccessFile(TestConstant.combinationPathName, "rw");

            // 获取源文件的长度
            long length = sourceFile.length();

            // 定义缓冲区大小
            int bufferSize = 100;

            // 定义读取和写入的位置
            long readPosition = 0;
            long writePosition = 0;

            // 逐个拷贝每个100字节的块
            while (readPosition < length) {
                // 设置源文件的读取位置
                sourceFile.seek(readPosition);

                // 读取100字节的数据到缓冲区
                byte[] buffer = new byte[bufferSize];
                int bytesRead = sourceFile.read(buffer);

                // 设置目标文件的写入位置
                targetFile.seek(writePosition);

                // 写入缓冲区的数据到目标文件
                targetFile.write(buffer, 0, bytesRead);

                // 更新读取和写入的位置
                readPosition += bufferSize;
                writePosition += bufferSize;
            }

            // 关闭文件
            sourceFile.close();
            targetFile.close();

            System.out.println("文件拷贝完成。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


