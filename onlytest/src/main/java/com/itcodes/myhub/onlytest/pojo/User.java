package com.itcodes.myhub.onlytest.pojo;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @ClassName User
 * @Author sussen
 * @Version 1.0
 * @Date 2020/3/20
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -3822233710107447629L;
    private String id;
    private String name;
    private String age;
    private String addr;
}
