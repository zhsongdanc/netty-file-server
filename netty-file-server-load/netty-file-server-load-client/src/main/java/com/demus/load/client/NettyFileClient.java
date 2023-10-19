package com.demus.load.client;

import com.demus.common.constant.Constant;
import com.demus.common.message.SimpleFileMessage;
import com.demus.common.unil.FileUtil;
import com.demus.load.client.encode.MyFileEncoder;
import com.demus.load.client.handler.TestClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/10/18 19:17
 */
@Slf4j
public class NettyFileClient {

    public static final String host = "127.0.0.1";

    public static final int port = 9909;

    public static void main(String[] args) throws InterruptedException {
        start();
    }

    public static void start() throws InterruptedException {

        SimpleFileMessage fileBO  = null;
        try {
            log.info("pathname:{}", Constant.pathname);
            fileBO = FileUtil.getFileBO(Constant.pathname);

        }catch (IOException e) {
            log.error("client get fileBO failed, file name:{}", Constant.pathname);
        }

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        try{
            SimpleFileMessage finalFileBO = fileBO;
            bootstrap.group(nioEventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {

//                            channel.pipeline().addLast(new StringEncoder());
                            channel.pipeline().addLast(new MyFileEncoder(finalFileBO.getFileSize(), finalFileBO.getFileBytes()));
                            channel.pipeline().addLast(new TestClientHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            log.info("connected!");
            channelFuture.channel().closeFuture().sync();
        }finally {
            log.info("client exit !");
            nioEventLoopGroup.shutdownGracefully();
        }
    }

}
