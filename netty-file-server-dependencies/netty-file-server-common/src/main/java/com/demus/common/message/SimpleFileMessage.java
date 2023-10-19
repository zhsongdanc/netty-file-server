package com.demus.common.message;

import lombok.Data;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/10/19 13:58
 */
@Data
public class SimpleFileMessage {

    private int fileSize;

    private byte[] fileBytes;
}
