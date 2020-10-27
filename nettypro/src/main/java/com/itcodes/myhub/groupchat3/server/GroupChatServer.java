package com.itcodes.myhub.groupchat3.server;

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

/**
 * @ClassName GroupChatServer 群聊服务端
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/22
 */
public class GroupChatServer {

    private final int port;

    public GroupChatServer(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    //使用NioServerSocketChannel作为服务端通道实现
                    .channel(NioServerSocketChannel.class)
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
                            //向管道链表添加 编码处理器
                            pipeline.addLast("decoder", new StringDecoder());
                            //向管道链表添加 解码处理器
                            pipeline.addLast("encoder", new StringEncoder());
                            //添加自定义的业务处理器
                            pipeline.addLast(new GroupChatServerHandler());
                        }
                    });
            System.out.println("服务器初始化完成");
            //绑定端口 并同步[启动服务端]
            ChannelFuture cf = b.bind(7000).sync();
            //监听通道关闭事件
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new GroupChatServer(7000).run();
    }
}
