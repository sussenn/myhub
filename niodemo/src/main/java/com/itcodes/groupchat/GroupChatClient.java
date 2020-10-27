package com.itcodes.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName GroupChatClient
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/17
 */
public class GroupChatClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    //构造器
    public GroupChatClient() {
        try {
            //获取选择器
            selector = Selector.open();
            //获取通道 设置非阻塞
            socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            socketChannel.configureBlocking(false);
            //将通道注册到选择器
            socketChannel.register(selector, SelectionKey.OP_READ);
            //获取用户信息
            username = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(username + "用户准备就绪...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //向服务端发送消息
    public void sendInfo(String info) {
        info = username + ":" + info;
        try {
            //创建缓冲区,将缓冲区数据写入通道
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取服务端回复的消息
    public void readInfo() {
        try {
            //通过选择器监听事件是否发生
            int count = selector.select(2000);
            if (count > 0) {
                //迭代遍历发生行为的事件
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isReadable()){
                        //获取对应的通道
                        SocketChannel sc = (SocketChannel) key.channel();
                        //创建缓冲区,将通道数据读取到缓冲区
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        sc.read(byteBuffer);
                        //把缓冲区数据转字符串 输出
                        String msg = new String(byteBuffer.array());
                        System.out.println(msg.trim());
                    }
                }
                //手动移除集合中已遍历的事件,防止重复操作
                keyIterator.remove();
            } /*else {
                System.out.println("未监听到有通道发生行为事件");
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GroupChatClient chatClient = new GroupChatClient();
        //读取消息
        new Thread(() -> {
            while (true) {
                chatClient.readInfo();
                try {
                    TimeUnit.SECONDS.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aaa").start();

        //控制台发送消息
        Scanner scanner = new Scanner(System.in);
        //如果控制台能读取到下一行
        while (scanner.hasNextLine()) {
            //获取下一行的输入信息
            String info = scanner.nextLine();
            //发送
            chatClient.sendInfo(info);
        }

    }
}
