package com.itcodes.myhub.hearbeat4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName DemoServer    netty心跳机制演示案例
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/23
 */
public class DemoServer {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    //使用NioServerSocketChannel作为服务端通道实现
                    .channel(NioServerSocketChannel.class)
                    //netty提供的日志输出
                    .handler(new LoggingHandler(LogLevel.INFO))
                    //设置线程连接等待个数(服务器可连接队列长度)
                    .option(ChannelOption.SO_BACKLOG, 16)
                    //保持线程活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //给workGroup设置管道处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //获取管道
                            ChannelPipeline pipeline = ch.pipeline();
                            /*
                             *空闲状态处理器.
                             * p1:超时n秒没有读事件,则发送心跳检测包,检测是否连接正常
                             * p2:超时n秒没有写事件,则...
                             * p3:超时n秒没有读写事件,则...
                             * 注意: 当IdleStateHandler触发,即会将信息传递给pipeline管道链表里的下一个处理器的userEventTiggered()方法去处理
                             *      故需要再自定义一个handler作指定处理
                             */
                            pipeline.addLast(new IdleStateHandler(3, 5, 7, TimeUnit.SECONDS));
                            //向管道链表添加 心跳机制触发事件 自定义处理器
                            pipeline.addLast(new DemoServerHandler());

                        }
                    });
            //绑定端口 并同步[启动服务端]
            ChannelFuture cf = b.bind(7000).sync();
            //监听通道关闭事件
            cf.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
