package com.itcodes.zerocopy.newmethod;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @ClassName NewIOServer   零拷贝示例--服务端
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/20
 */
public class NewIOServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress(7001);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(address);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readCount = 0;
            while (readCount != -1) {
                readCount = socketChannel.read(byteBuffer);
                //缓冲区倒带 position =0 mark清除
                byteBuffer.rewind();
            }
        }
    }
}
