package com.itcodes.myhub.hearbeat4;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @ClassName DemoServerHandler 心跳机制 读写空闲事件发生 处理器
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/23
 */
public class DemoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            //将obj类型向下转型
            IdleStateEvent event = (IdleStateEvent) evt;

            String eventType = null;
            switch (event.state()){
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }
            Channel channel = ctx.channel();
            //TODO 拿到对应管道,进行相应处理...

        }
    }
}
