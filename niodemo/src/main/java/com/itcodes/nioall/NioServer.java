package com.itcodes.nioall;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName NioServer
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/16
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        //创建服务端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //获取多路复用器(选择器)
        Selector selector = Selector.open();
        //绑定需要监听的端口主机(客户端)
        serverSocketChannel.socket().bind(new InetSocketAddress(9900));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //多路复用器的设置  关心事件为"新连接事件"
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while (true){
            //阻塞等待1s,没有事件发生则返回
            if (selector.select(1000) == 0){
                System.out.println("服务器等待事件超时...");
                continue;
            }
            //获取监听到的 发生事件 集合    selector.kes() 获取所有注册到选择器的(未发生行为和已发生行为的)事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //迭代遍历
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey selKey = keyIterator.next();
                //判断事件的行为
                //新客户端连接行为
                if (selKey.isAcceptable()){
                    //连接客户端 [阻塞方法]
                    SocketChannel accChannel = serverSocketChannel.accept();
                    //将客户端连接设为非阻塞
                    accChannel.configureBlocking(false);
                    System.out.println("客户端连接成功,生成对应accChannel通道:" + accChannel.hashCode());
                    //将连接的客户端注册到多路复用器   关心事件为"OP_READ" 同时关联缓冲区
                    accChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                //读行为
                if (selKey.isReadable()){
                    //通过事件key,反向获取对应通道
                    SocketChannel readChannel = (SocketChannel) selKey.channel();
                    //获取此通道关联的buffer缓冲区
                    ByteBuffer readBuffer = (ByteBuffer) selKey.attachment();
                    //从通道读取数据到缓冲区
                    readChannel.read(readBuffer);
                    System.out.println("客户端发送的数据是:" + new String(readBuffer.array()));
                }
                //手动移除集合中已遍历的事件
                keyIterator.remove();
            }
        }
    }
}
