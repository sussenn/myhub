package com.itcodes.excepthandler.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName User
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1849783783843431440L;
    private Long id;
    private String uname;
    private int sex;
}
