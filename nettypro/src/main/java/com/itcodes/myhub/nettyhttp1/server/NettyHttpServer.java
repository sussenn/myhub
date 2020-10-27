package com.itcodes.myhub.nettyhttp1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName NettyHttpServer   netty网络通信 服务端(客户端即浏览器)
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/21
 */
public class NettyHttpServer {
    public static void main(String[] args) {
        //处理连接事件 默认线程数: cpu核数*2
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //处理读写事件 默认线程数: cpu核数*2
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup)
                    //使用NioServerSocketChannel作为服务端通道实现
                    .channel(NioServerSocketChannel.class)
                    //设置线程连接等待个数(服务器可连接队列长度)
                    .option(ChannelOption.SO_BACKLOG, 16)
                    //保持线程活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //给workGroup设置管道处理器
                    .childHandler(new NettyHttpServerInit());

            ChannelFuture cf = serverBootstrap.bind(9095).sync();
            cf.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //断开连接,关闭线程
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
