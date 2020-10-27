package com.itcodes.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @ClassName GroupChatServer   群聊服务端
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/17
 */
public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    //构造器
    public GroupChatServer() {
        try {
            //获取 选择器
            selector = Selector.open();
            //获取 服务端通道
            listenChannel = ServerSocketChannel.open();
            //绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //设置非阻塞
            listenChannel.configureBlocking(false);
            //将通道注册到选择器
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //选择器进行监听事件发生
    public void listen() {
        try {
            while (true) {
                //监听事件发生 超时1s
                int count = selector.select(1000);
                if (count > 0) {
                    //迭代遍历发生行为的事件
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        //如果是 新连接事件 OP_ACCEPT
                        if (key.isAcceptable()) {
                            //通道连接到客户端  设置非阻塞
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            //将此通道注册到选择器  读行为,读取客户端发送的信息
                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + "用户上线");
                        }
                        //如果是 读事件 OP_READ
                        if (key.isReadable()) {
                            //进行读行为的处理
                            readData(key);
                        }
                        //手动移除集合中已遍历的事件,防止重复操作
                        keyIterator.remove();
                    }
                } /*else {
                    System.out.println("无事件处理,等待中...");
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取客户端发送的信息
    private void readData(SelectionKey key) {
        SocketChannel socketChannel = null;
        try {
            //通过事件key获取关联通道
            socketChannel = (SocketChannel) key.channel();
            //创建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //将通道数据读取到缓冲区
            int readCount = socketChannel.read(byteBuffer);
            //如果有数据
            if (readCount > 0) {
                String msg = new String(byteBuffer.array());
                System.out.println("接收到客户端消息:" + msg);
                //向其他客户端(通道)转发消息(排除当前客户端)
                sendInfo2OtherClients(msg, socketChannel);
                System.out.println("转发消息OK...");
            }
        } catch (IOException e) {
            try {
                System.out.println(socketChannel.getRemoteAddress() + "用户离线...");
                //取消此通道的注册
                key.cancel();
                //关闭通道
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    //向其他客户端(通道)转发消息
    private void sendInfo2OtherClients(String msg, SocketChannel socketChannel) {
        try {
            //遍历选择器中所有注册的通道  排除当前客户端
            for (SelectionKey key : selector.keys()) {
                //通过key获取对应的通道
                Channel targetChannel =  key.channel();
                //排除当前客户端的通道
                if (targetChannel instanceof SocketChannel && targetChannel != socketChannel) {
                    //类型强转
                    SocketChannel dest = (SocketChannel) targetChannel;
                    //创建缓冲区 将消息存储到缓存区
                    ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                    //将缓冲区消息写出到通道
                    dest.write(byteBuffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GroupChatServer chatServer = new GroupChatServer();
        chatServer.listen();
    }
}
