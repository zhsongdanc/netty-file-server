package com.demus.load.client.handler;

import com.demus.common.constant.Constant;
import com.demus.common.message.SimpleFileMessage;
import com.demus.common.unil.FileUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/10/18 19:18
 */
public class TestClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        SimpleFileMessage fileBO = FileUtil.getFileBO(Constant.pathname);

        ctx.channel().writeAndFlush(fileBO);

//        super.channelActive(ctx);
    }
}
