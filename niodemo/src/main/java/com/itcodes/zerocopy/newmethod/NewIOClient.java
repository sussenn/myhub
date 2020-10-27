package com.itcodes.zerocopy.newmethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @ClassName NewIOClient   零拷贝示例--客户端
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/20
 */
public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",7001));
        String fileName = "c:\\protoc-3.6.1-win32.zip";

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long startTime = System.currentTimeMillis();

        //在windows 一次调用transferTo() 只能发送8M. Linux无限制
        //零拷贝
        long tranCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送总字节数:" + tranCount + ",耗时:" + (System.currentTimeMillis() - startTime));

        fileChannel.close();
    }
}
