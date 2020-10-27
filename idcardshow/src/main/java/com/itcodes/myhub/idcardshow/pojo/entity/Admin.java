package com.itcodes.myhub.idcardshow.pojo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName Admin
 * @Author sussen
 * @Version 1.0
 * @Data 2019/8/22
 */
@Entity
@Table(name = "admin")
@Data
public class Admin implements Serializable {

    private static final long serialVersionUID = 4916752131498732269L;
    @Id
    private Long aid;
    private String username;
    private String password;
    private String nikename;
    private String avatar;
    private String status;
}
