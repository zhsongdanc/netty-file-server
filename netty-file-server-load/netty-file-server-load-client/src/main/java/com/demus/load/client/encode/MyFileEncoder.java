package com.demus.load.client.encode;

import com.demus.common.message.SimpleFileMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.internal.ObjectUtil;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/10/19 13:43
 */
public class MyFileEncoder extends MessageToMessageEncoder<SimpleFileMessage> {

    private final long fileSize;
    private byte[] fileBytes;


    public MyFileEncoder(long fileSize, byte[] bytes) {
        assert fileSize == bytes.length;
        this.fileSize = fileSize;
        this.fileBytes = bytes;
    }

    // 注意：这里out里面添加两次，就会触发server端两次channelRead
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, SimpleFileMessage simpleFileMessage,
            List<Object> out) throws Exception {

        long fileSize = simpleFileMessage.getFileSize();

        // todo buffer方法为什么要用大端存储
        ByteBuf byteBuf = Unpooled.buffer(8 );
        byteBuf.writeLong(fileSize);
        out.add(byteBuf);

        ByteBuf fileBytes = Unpooled.wrappedBuffer(simpleFileMessage.getFileBytes());
        out.add(fileBytes);




//                    if (fileSize > 0) {
//                int fileSize = simpleFileMessage.getFileSize();
//
//                // todo buffer方法为什么要用大端存储
//                ByteBuf byteBuf = Unpooled.buffer(8);
//                byteBuf.writeLong(fileSize);
//                out.add(byteBuf);
//
//                ByteBuf fileByteBuf = Unpooled.wrappedBuffer(fileBytes);
//
//                SimpleFileMessage simpleFileMessage1
//                out.add(fileByteBuf);

    }

}

