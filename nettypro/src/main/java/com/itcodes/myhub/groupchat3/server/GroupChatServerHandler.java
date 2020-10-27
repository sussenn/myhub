package com.itcodes.myhub.groupchat3.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName GroupChatServerHandler    自定义业务处理器(string)
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/22
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    //一对一单聊实现 k:用户id, v:关联通道 (k也可以使用user对象,存储登录信息)
    //在channelRead0()实现用户信息交互
    //private static Map<String,Channel> channelMap = new HashMap<>();

    //用于管理多个客户端通道的组. GlobalEventExecutor.INSTANCE 全局事件执行器,单例.
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 当连接建立,即触发的首个方法
     * 1.将连接的客户端加入管理组
     * 2.给所有在线客户端推送其他客户端上线信息
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "加入聊天\n");
        channelGroup.add(channel);

        //一对一单聊实现
        //channelMap.put("用户id",channel);
    }

    /**
     * 当连接断开,则触发的方法 [该方法会自动将管理组里关联通道移除]
     * <注意> 可能存在连接断开而服务端无法感知的特殊状况(如网络波动). 需使用 心跳机制
     * 给所有在线客户端推送其他客户端离开聊天信息
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelGroup.writeAndFlush("[客户端]" + ctx.channel().remoteAddress() +"离开聊天\n");
        System.out.println("当前管理组通道个数:" + channelGroup.size());
    }

    /**
     * 管道连接处于活动状态时,进行的业务
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "用户上线");
    }

    /**
     * 管道连接处于非活动状态时,进行的业务
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "用户离线");
    }

    /**
     * 客户端和服务端数据交换处理
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            //排除发送消息本身客户端,将消息转发给其他客户端
            if (ch != channel){
                ch.writeAndFlush("[客户端]" + channel.remoteAddress() + "说: " + msg + "\n");
            }else {
                ch.writeAndFlush("[我]说: " + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
