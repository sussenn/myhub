package com.itcodes.myhub.protobuf7.server;

import com.itcodes.myhub.protobuf7.MyDataInfo;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @ClassName NettyServerHandler    自定义工作线程组 管道处理器
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/21
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    //处理通道 读取客户发送数据 事件
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();
        if (dataType == MyDataInfo.MyMessage.DataType.StudentType){
            MyDataInfo.Student student = msg.getStudent();
            System.out.println("student:" + student.getId()+"---"+student.getName());
        }else if (dataType == MyDataInfo.MyMessage.DataType.WorkType){
            MyDataInfo.Worker worker = msg.getWorker();
            System.out.println("worker:" + worker.getAge()+"---"+worker.getName());
        }else {
            System.out.println("传输类型不合法");
        }
    }

    /**
     * 读取数据后的操作
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将读取的数据写入缓冲区并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 小客户", CharsetUtil.UTF_8));
    }


    /**
     * 发生异常的处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //直接关闭通道
        ctx.close();
    }
}
