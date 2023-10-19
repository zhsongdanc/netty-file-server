package com.demus.common.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/10/19 13:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleFileMessage {

    private long fileSize;

    private byte[] fileBytes;
}
