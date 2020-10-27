package com.itcodes.myhub.nettyhttp1.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @ClassName NettyHttpServerHandler 自定义处理器
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/21
 */
//继承自ChannelInboundHandlerAdapter
//HttpObject: 客户端与服务端通讯的数据被封装成的对象
public class NettyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * 处理通道 读取客户端数据 事件
     * @param ctx 可以获取该handler关联通道/管道等信息
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        /*//因为服务器会发送2次请求,获取网站地址图标. 需进行指定过滤
        HttpRequest httpRequest = (HttpRequest) msg;
        URI uri = new URI(httpRequest.uri());
        if (uri.getPath().equals("/favicon.ico")){
            return;
        }*/

        //判断msg是否属于httpRequest
        if (msg instanceof HttpRequest){
            //SocketAddress address = ctx.channel().remoteAddress();  //客户端地址
            //回复信息给浏览器 使用http协议
            ByteBuf content = Unpooled.copiedBuffer("hello, I'm Server", CharsetUtil.UTF_8);
            //将回复信息 构造为httpResponse
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            //设置响应头 文本类型
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/palin");
            //响应信息的长度
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            //返回构造后的信息
            ctx.writeAndFlush(response);
        }
    }

    //exceptionCaught() 异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
