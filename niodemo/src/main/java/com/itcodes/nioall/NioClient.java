package com.itcodes.nioall;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @ClassName NioClient
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/16
 */
public class NioClient {
    public static void main(String[] args) throws IOException {
        //获取客户端网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //提供服务器端 ip
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 9900);

        //如果客户端连接失败
        if (!socketChannel.connect(socketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("客户端连接失败,非阻塞,可进行其他工作处理...");
            }
        }

        //连接成功,则发送数据到客户端
        String str = "hello world 你好";
        //创建与数据大小一致的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        //发送数据. 即将缓冲区数据写入通道
        socketChannel.write(byteBuffer);

        System.in.read();
    }
}
