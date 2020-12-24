package com.itc.excel.dao;

import com.itc.excel.bean.Points;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName PointsDao
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/12/18
 */
@Repository
@Mapper
public interface PointsDao {
    //@Select("SELECT cdkey, start_time, end_time,points FROM ser_points2 WHERE points = #{points}")
    List<Points> findByPoi(@Param("points") long points);
}
