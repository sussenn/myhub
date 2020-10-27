package com.itcodes.myhub.websocket5.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @ClassName WebServer 服务器与浏览器交互 长连接演示案例
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/23
 */
public class WebServer {
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
                             * 基于http协议交互,需添加http编解码器
                             * 底层使用Java序列化,效率低,数据体积变大,无法跨语言使用
                             * 推荐使用Google的 [ProtoBuf]
                             */
                            pipeline.addLast(new HttpServerCodec());
                            //
                            pipeline.addLast(new ChunkedWriteHandler());
                            //http数据传输是分段进行,添加此处理器将分段数据聚合
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            /*
                             * websocket数据传递以 帧(frame)形式进行
                             * 此处理器可将http协议升级为ws协议, 保持长连接
                             * 浏览器发送请求时,使用的路径是: ws://localhost:7000/demo
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/demo"));
                            //自定义业务处理器
                            pipeline.addLast(new WebServerFrameHandler());
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
