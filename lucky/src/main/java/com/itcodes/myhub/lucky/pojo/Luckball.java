package com.itcodes.myhub.lucky.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Luckball
 * @Author sussen
 * @Version 1.0
 * @Data 2019/11/22
 */
@Data
public class Luckball implements Serializable {

    private static final long serialVersionUID = 2661010471217021157L;
    private String redLuck1;
    private String redLuck2;
    private String redLuck3;
    private String redLuck4;
    private String redLuck5;
    private String redLuck6;

    private String blueLuck;

}
