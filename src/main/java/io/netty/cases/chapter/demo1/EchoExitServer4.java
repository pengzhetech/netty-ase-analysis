package io.netty.cases.chapter.demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.logging.Logger;

/**
 * Created by ���ַ� on 2018/8/3.
 */
public class EchoExitServer4 {

    static Logger logger = Logger.getLogger(EchoExitServer4.class.getName());

    public static void main(String[] args) throws Exception {
        final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        final EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new LoggingHandler(LogLevel.INFO));
                        }
                    });
            ChannelFuture f = b.bind(18080).sync();
            f.channel().closeFuture().addListener(new ChannelFutureListener() {
                // @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    //ҵ���߼�������룬�˴�ʡ��...
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                    logger.info(future.channel().toString() + " ��·�ر�");
                }
            });
        } finally {
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
        }
    }
}
