package com.itcodes.myhub.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 获取系统当前时间格式为：2019-07-04 16:06:33
     * @return
     */
    public static String getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
