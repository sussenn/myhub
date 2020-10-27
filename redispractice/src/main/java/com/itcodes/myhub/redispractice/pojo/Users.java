package com.itcodes.myhub.redispractice.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName Users
 * @Author sussen
 * @Version 1.0
 * @Data 2020/3/19
 */
@Entity
@Table(name = "useres")
@Data
public class Users implements Serializable {

    private static final long serialVersionUID = 6356917046319791472L;
    @Id
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
