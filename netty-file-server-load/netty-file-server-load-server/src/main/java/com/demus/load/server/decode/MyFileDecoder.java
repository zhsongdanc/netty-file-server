package com.demus.load.server.decode;

import com.demus.common.constant.AppendableBytes;
import com.demus.common.message.SimpleFileMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/10/19 19:44
 */
@Slf4j
public class MyFileDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final long fileSize;
    private byte[] fileBytes;


    public MyFileDecoder(long fileSize, byte[] bytes) {
        assert fileSize == bytes.length;
        this.fileSize = fileSize;
        this.fileBytes = bytes;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out)
            throws Exception {

        int readableBytes = byteBuf.readableBytes();
        if (readableBytes < 8) {
            log.error("reading length field, but readable bytes = {}", readableBytes);
            return;
        }

        long fileSize = byteBuf.readLong();
        int fileContentSize = byteBuf.readableBytes();
        if (byteBuf.readableBytes() < fileSize) {
            log.error("reading file content, but readable bytes = {}", fileContentSize);
            byteBuf.resetReaderIndex();
            return;
        }

        // todo 这里可能丢失精度或溢出
        int intSize = (int)fileSize;
        byte[] fileBytes = new byte[intSize];
        byteBuf.readBytes(fileBytes);

        SimpleFileMessage simpleFileMessage = new SimpleFileMessage();
        simpleFileMessage.setFileSize(intSize);
        simpleFileMessage.setFileBytes(fileBytes);

        out.add(simpleFileMessage);
    }
}
