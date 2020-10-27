package com.itcodes.niobuffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @ClassName ScatterGatherDemo
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/16
 * Scattering: 写入数据到buffer时, 使用数组, 依次写入 [分散]
 * Gathering: 从buffer读数据时, 使用数组, 依次读取   [聚合]
 */
public class ScatterGatherDemo {
    public static void main(String[] args) throws IOException {
        //服务端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //客户端
        InetSocketAddress socketAddress = new InetSocketAddress(7000);
        //绑定客户端到服务器端
        serverSocketChannel.socket().bind(socketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();

        //假定从客户端接收8个字节
        int mesgLen = 8;
        //循环读取
        while (true) {
            int byteRead = 0;
            while (byteRead < mesgLen) {
                long read = socketChannel.read(byteBuffers);
                byteRead += read;
                System.out.println("读取的数据:" + read);
                //使用流式打印. 查看当前buffer的position 和limit
                Arrays.asList(byteBuffers).stream().map(buffer -> "position=" + buffer.position() + ",limit=" + buffer.limit()).forEach(System.out::println);
            }

            //将所有buffer读写切换
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());
            //将数据从buffer读出,回显到客户端
            long byteWrite = 0;
            while (byteWrite < mesgLen) {
                long write = socketChannel.write(byteBuffers);
                byteWrite += write;
            }

            //清空缓冲区
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.clear());

            System.out.println("Read=" + byteRead + ",write=" + byteWrite + ",mesglen=" + mesgLen);

        }

    }
}
