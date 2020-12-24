package com.itc.excel.service;

import com.itc.excel.ExcelHandleMain;
import com.itc.excel.bean.Points;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = ExcelHandleMain.class)
@RunWith(value = SpringRunner.class)
public class HuToolExcelServiceTest {

    @Autowired
    private HuToolExcelService service;



    @Test
    public void test00() {
        List<Points> points = service.findByPoi(5);
        service.toWrite(points,"D:\\data\\points-l\\5积分.xlsx");
    }

    @Test
    public void test001() {
        List<Points> points = service.findByPoi(10);
        service.toWrite(points,"D:\\data\\points-l\\10积分.xlsx");
    }

    @Test
    public void test002() {
        List<Points> points = service.findByPoi(20);
        service.toWrite(points,"D:\\data\\points-l\\20积分.xlsx");
    }


    @Test
    public void test003() {
        List<Points> points = service.findByPoi(50);
        service.toWrite(points,"D:\\data\\points-l\\50积分.xlsx");
    }

    //@Test
    //public void test06() {
    //    List<Points> points = service.findByPoi(1);
    //    service.toWrite(points,"D:\\data\\points-l\\1积分.xlsx");
    //}
    //@Test
    //public void test00() {
    //    service.read("D:\\data\\points\\meta\\ser_points2.xlsx");
    //}

}