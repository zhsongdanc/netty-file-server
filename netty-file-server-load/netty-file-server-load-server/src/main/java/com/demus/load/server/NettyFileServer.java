package com.demus.load.server;

import com.demus.common.constant.Constant;
import com.demus.common.message.SimpleFileMessage;
import com.demus.common.unil.FileUtil;
import com.demus.load.server.decode.MyFileDecoder;
import com.demus.load.server.handler.TestServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/10/18 18:55
 */
@Slf4j
public class NettyFileServer {

    public static final int port = 9909;

    // todo 这里直接将InterruptedException抛出去是否合适？
    public static void main(String[] args) throws InterruptedException {
        startServer();
    }

    public static void startServer() throws InterruptedException {

        SimpleFileMessage fileBO  = null;
        try {
            log.info("pathname:{}",Constant.pathname);
            fileBO = FileUtil.getFileBO(Constant.pathname);

        }catch (IOException e) {
            log.error("server get fileBO failed, file name:{}", Constant.pathname);
        }

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            SimpleFileMessage finalFileBO = fileBO;
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new MyFileDecoder(finalFileBO.getFileSize(), finalFileBO.getFileBytes()));
                            ch.pipeline().addLast(new TestServerHandler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            log.info("Server has started!");
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
