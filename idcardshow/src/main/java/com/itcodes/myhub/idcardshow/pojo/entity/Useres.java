package com.itcodes.myhub.idcardshow.pojo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName Useres
 * @Author sussen
 * @Version 1.0
 * @Data 2019/8/22
 */
@Entity
@Table(name = "useres")
@Data
public class Useres implements Serializable {
    private static final long serialVersionUID = 7569592408948138009L;
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
