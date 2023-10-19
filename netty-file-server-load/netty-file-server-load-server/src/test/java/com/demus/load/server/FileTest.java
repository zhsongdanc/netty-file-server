package com.demus.load.server;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/10/19 12:07
 */

import com.demus.common.unil.FileUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileTest {


    @Before
    public void setup() {

    }

    @Test
    public void test() throws IOException {
        String filePath = "";
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = FileUtil.toBytes(inputStream);

        ByteBuf byteBuf = Unpooled.buffer(bytes.length);


    }

    @After
    public void teardown() {

    }

}
