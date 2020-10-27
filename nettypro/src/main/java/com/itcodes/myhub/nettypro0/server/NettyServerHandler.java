package com.itcodes.myhub.nettypro0.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName NettyServerHandler    自定义工作线程组 管道处理器
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/21
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 处理通道 读取客户发送数据 事件
     *
     * @param ctx 上下文对象. 包含管道pipeline,通道channel,连接地址
     * @param msg 客户端发送的信息
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //异步执行 [将该通道(channel)提交到对应事件组(eventLoop)的任务队列(taskQueue)中. 这些对象都封装在ctx的pipeline中]
        //方式1. 自定义普通任务. [多个任务则按队列顺序执行.即该任务队列是单线程处理]
        ctx.channel().eventLoop().execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                ctx.writeAndFlush(Unpooled.copiedBuffer("任务队列处理,读取客户端数据ing...10s...", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //方式2. 自定义定时任务. [注意:定时任务将提交到scheduleTaskQueue]
        ctx.channel().eventLoop().schedule(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                ctx.writeAndFlush(Unpooled.copiedBuffer("定时任务队列处理,读取客户端数据ing...5s...", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },5L,TimeUnit.SECONDS);

        /*//将msg转换为ByteBuf
        ByteBuf buf = (ByteBuf) msg;
        String msgs = buf.toString(CharsetUtil.UTF_8);  //byteBuf转字符串
        System.out.println("客户端消息:" + msgs);
        SocketAddress address = ctx.channel().remoteAddress();  //获取客户端地址
        System.out.println("客户端地址:" + address);*/

        //super.channelRead(ctx, msg);
    }

    /**
     * 读取数据后的操作
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将读取的数据写入缓冲区并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 小客户", CharsetUtil.UTF_8));

        //super.channelReadComplete(ctx);
    }


    /**
     * 发生异常的处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //直接关闭通道
        ctx.close();
        //super.exceptionCaught(ctx, cause);
    }
}
