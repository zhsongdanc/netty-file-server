package com.demus.load.server.handler;

import com.demus.common.constant.Constant;
import com.demus.common.message.SimpleFileMessage;
import com.demus.common.unil.FileUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/10/18 19:14
 */
@Slf4j
public class TestServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("receive client msg !");
        if (msg instanceof SimpleFileMessage) {
            log.info("receiving SimpleFileMessage msg !");
            FileUtil.saveFile((SimpleFileMessage) msg, Constant.savePathname);

        }
//        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client {} connected!", ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }


}
