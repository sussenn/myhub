package com.itc.amqp.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName User
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/17
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -6644079687489571261L;
    private String id;
    private String name;
    private String password;
    private int sex;
}
