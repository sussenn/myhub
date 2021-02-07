package com.itcodes.myhub.redistemplatepractice.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Users
 * @Author sussen
 * @Version 1.0
 * @Data 2020/3/19
 */
@Data
@SuppressWarnings("serial")   //为被序列化类自动生成一个随机的序列化ID    抑制没有声明serialVersionUID变量警告
public class User implements Serializable {

    //private static final long serialVersionUID = 6356917046319791472L;
    private Long id;
    private String name;
    private String sex;
    private String phone;
    private String idcard;
    private String address;
    private String sid;
    private String status;
    private String remark;
}
