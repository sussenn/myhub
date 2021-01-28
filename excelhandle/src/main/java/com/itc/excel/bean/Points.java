package com.itc.excel.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Points
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/12/17
 */
@Data
public class Points implements Serializable {
    private static final long serialVersionUID = -1222658238081408473L;

    private String id;
    private Long points;
    private String cdkey;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date start_time;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date end_time;

}
