package com.itcodes.myhub.syslog.pojo;

import java.io.Serializable;

/**
 * @ClassName User
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/17
 */
public class User implements Serializable {
    private static final long serialVersionUID = 4688215583169905664L;

    private String id;
    private String name;
    private String password;
    private int sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                '}';
    }
}
