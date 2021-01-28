package com.itcodes.myhub.mybatisboot.pojo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Author sussen
 * @Version 1.0
 * @Data 2020/1/3
 */
@Data
@SuppressWarnings("serial")   //为被序列化类自动生成一个随机的序列化ID    抑制没有声明serialVersionUID变量警告
public class User implements Serializable {

    //private static final long serialVersionUID = 3209734620974150542L;
    private Long uid;
    private String uname;
    private String sex;
    private Integer age;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
