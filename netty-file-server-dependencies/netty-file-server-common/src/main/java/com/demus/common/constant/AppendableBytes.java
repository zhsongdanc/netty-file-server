package com.demus.common.constant;

import java.util.Arrays;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/10/20 14:53
 */
// 使用该类并不难解决发送方拆包问题，因为我最初写的decoder，每次都会先去解码length字段
public class AppendableBytes {

    private byte[] bytes = new byte[4096];
    private int size = 0;


    private void append(byte[] appendBytes) {
        while (bytes.length - size < appendBytes.length) {
            bytes = Arrays.copyOf(this.bytes, bytes.length * 2);
        }
        System.arraycopy(appendBytes, 0, bytes, size, appendBytes.length);
        size+=appendBytes.length;
    }



    private  byte[] getBytes() {
        byte[] tmp = new byte[size];
        System.arraycopy(bytes, 0, tmp, 0, size);
        return tmp;
    }
}
