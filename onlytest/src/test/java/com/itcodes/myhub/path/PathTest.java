package com.itcodes.myhub.path;

import com.itcodes.myhub.onlytest.OnlytestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Iterator;
import java.util.List;

/**
 * Paths工具类的使用
 * https://mp.weixin.qq.com/s/WGP7ve1IBrlKLRJG41W5Gw
 */
@SpringBootTest(classes = OnlytestApplication.class)
@RunWith(value = SpringRunner.class)
public class PathTest {

    @Test
    public void test00() {
        Path path = Paths.get("/Logging/OnlyTest/OnlyTest-info-2020-03-20-0.log");
        //获取路径中的文件名或者最后一个节点元素
        System.out.printf("FileName:%s%n", path.getFileName()); //FileName:OnlyTest-info-2020-03-20-0.log
        //路径节点元素的格式
        System.out.printf("NameCount:%s%n", path.getNameCount());   //NameCount:3

        //遍历路径节点方法1
        Iterator<Path> names = path.iterator();
        int i = 0;
        while (names.hasNext()) {
            Path name = names.next();
            System.out.printf("Name %s:%s%n", i, name.toString());
            //Name 0:Logging
            //Name 1:OnlyTest
            //Name 2:OnlyTest-info-2020-03-20-0.log
            i++;
        }

        //方法2
        for (int j = 0; j < path.getNameCount(); j++) {
            System.out.printf("Name %s:%s%n", j, path.getName(j));
        }

        //父路径
        System.out.printf("Parent:%s%n", path.getParent());  //Parent:\Logging\OnlyTest
        //跟路径,比如"/"、"C:";如果是相对路径，则返回null。
        System.out.printf("Root:%s%n", path.getRoot());      //Root:\
        //子路径，结果中不包含root，前开后闭
        System.out.printf("Subpath[0,2]:%s%n", path.subpath(0, 2));   //Subpath[0,2]:Logging\OnlyTest

    }

    //去掉冗余路径 /../
    @Test
    public void test01() {
        Path path = Paths.get("/Logging/OnlyTest/../OnlyTest-info-2020-03-20-0.log");
        System.out.printf("%s%n", path.normalize());     // \Logging\OnlyTest-info-2020-03-20-0.log
    }

    //获取系统本地文件的路径
    @Test
    public void test02() {
        Path path = Paths.get("/Logging/OnlyTest/OnlyTest-info-2020-03-20-0.log");
        System.out.printf("%s%n", path.toUri());     //file:///C:/Logging/OnlyTest/OnlyTest-info-2020-03-20-0.log
    }

    //文件是否存在.   文件读写权限判断
    @Test
    public void test03() {
        //判断文件是否具有 写、读、执行的权限
        Path path = Paths.get("/data/temp/path/pathText.txt");
        boolean isExist = Files.isRegularFile(path) & Files.isReadable(path) & Files.isExecutable(path);
        System.out.println(isExist);

        //检测文件是否存在
        boolean exists = Files.exists(path);
        System.out.println(exists);
        //检测文件是否不存在     一般和!Files.exists(path) 配合使用
        boolean notExists = Files.notExists(path);
        System.out.println(notExists);
    }

    //文件拷贝
    @Test
    public void test04() throws IOException {
        Path source = Paths.get("/data/temp/path/pathText.txt");
        Path target = Paths.get("/data/temp/path/pathText.txt.copy");
        //REPLACE_EXISTING: 如果目标文件已经存在，则直接覆盖
        //COPY_ATTRIBUTES：复制文件时，也同时复制目标文件的属性
        //NOFOLLOW_LINKS：如果文件是软连接，只复制连接文件，不复制其实际文件内容
        Path copy = Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES,
                LinkOption.NOFOLLOW_LINKS);
        System.out.println(copy);
    }

    //文件读写
    @Test
    public void test05() throws IOException {
        //全部读取小文件中的数据
        //byte[] bytes = Files.readAllBytes(Paths.get("/data/temp/path/pathText.txt"));
        List<String> lines = Files.readAllLines(Paths.get("/data/temp/path/pathText.txt"), Charset.forName("utf-8"));
        System.out.println(lines);

        //将准备好的数据，直接全部写入文件(拷贝)。[打开、写入]
        Path write = Files.write(Paths.get("/data/temp/path/pathText-test.txt"), lines, Charset.forName("utf-8"),
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE);

    }

    @Test
    public void test06() throws IOException {
        Path path = Paths.get("/data/temp/path/pathText.txt");
        //创建一个普通文件，如果已存在则抛出异常，文件属性为默认。
        //Files.createFile(path);

        //创建临时文件，临时文件的目录和前缀可以为null，后缀如果为null时默认使用".tmp";
        //Files.createTempFile(null, null, ".tmp");
        //创建一个软连接文件
        //客户端没有所需的特权    cmd运行: icacls c:\ /setintegritylevel M
        Files.createSymbolicLink(Paths.get("/data/temp/path/pathTextLog.txt"), Paths.get("/data/temp/path/pathText.txt"));

    }
}