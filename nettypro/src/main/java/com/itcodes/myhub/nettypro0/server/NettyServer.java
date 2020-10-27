package com.itcodes.myhub.nettypro0.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName NettyServer
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/21
 */
public class NettyServer {
    public static void main(String[] args) {
        //创建bossGroup 处理连接事件
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //创建workGroup 处理读写事件
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            //创建服务端启动对象.
            ServerBootstrap bootstrap = new ServerBootstrap();
            //配置服务端参数
            bootstrap.group(bossGroup, workGroup)  //设置boss和work线程组
                    .channel(NioServerSocketChannel.class)  //使用NioServerSocketChannel作为服务端通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)  //设置线程连接等待个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)  //保持线程活动连接
                    //设置workGroup工作线程组的 管道处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            //使用自定义处理器
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            //绑定端口 并同步[启动服务端]
            ChannelFuture cf = bootstrap.bind(6668).sync();
            //注册监听器,获取(绑定端口,建立连接的)事件行为结果
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (cf.isSuccess()){
                        System.out.println("绑定端口成功");
                    }
                }
            });
            //监听通道关闭事件
            cf.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
