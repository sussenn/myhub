package com.itc.excel.service;

import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import com.itc.excel.bean.Points;
import com.itc.excel.dao.PointsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName HuToolExcelService
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/12/17
 */
@Service
public class HuToolExcelService {

    @Autowired
    private PointsDao pointsDao;

    //public void read(String path) {
    //    ArrayList<Points> points = new ArrayList<>();
    //    ExcelUtil.readBySax(path, 0, createRowHandler());
    //}
    //
    //private void write1(List<Points> list) {
    //    BigExcelWriter writer = ExcelUtil.getBigWriter("D:\\data\\points\\1积分.xlsx");
    //    // 一次性写出内容，使用默认样式
    //    writer.write(list);
    //    // 关闭writer，释放内存
    //    writer.close();
    //}
    //
    //private RowHandler createRowHandler() {
    //    List<Points> linkedList = new LinkedList<>();
    //    return (i, l, list) -> {
    //        list.forEach(s -> {
    //
    //        });
    //
    //    };
    //}


    public List<Points> findByPoi(long points) {
        return pointsDao.findByPoi(points);
    }

    public void toWrite(List<Points> list, String path) {
        //BigExcelWriter writer = ExcelUtil.getBigWriter("D:\\data\\points\\1积分.xlsx");
        BigExcelWriter writer = ExcelUtil.getBigWriter(path);
        // 一次性写出内容，使用默认样式
        writer.write(list);
        // 关闭writer，释放内存
        writer.close();
    }

}
