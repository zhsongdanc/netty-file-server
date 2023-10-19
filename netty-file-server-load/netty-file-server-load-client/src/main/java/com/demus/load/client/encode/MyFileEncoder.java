package com.demus.load.client.encode;

import io.netty.buffer.ByteBufUtil;
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
public class MyFileEncoder extends MessageToMessageEncoder<CharSequence> {
    private final Charset charset;

    public MyFileEncoder() {
        this(Charset.defaultCharset());
    }

    public MyFileEncoder(Charset charset) {
        this.charset = (Charset) ObjectUtil.checkNotNull(charset, "charset");
    }

    protected void encode(ChannelHandlerContext ctx, CharSequence msg, List<Object> out) throws Exception {
        if (msg.length() != 0) {
            out.add(ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(msg), this.charset));
        }
    }
}

