package com.itcodes.myhub.idcardshow.service;

import com.itcodes.myhub.idcardshow.IdcardShowApplication;
import com.itcodes.myhub.idcardshow.pojo.entity.Admin;
import com.itcodes.myhub.util.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = IdcardShowApplication.class)
@RunWith(value = SpringRunner.class)
public class UseresServiceTest {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void addAdmin() {
        Admin admin = new Admin();
        admin.setUsername("user");
        admin.setPassword("123456");
        admin.setAvatar("https://cn.bing.com/th?id=OIP.OixcxS8Zgcwp7MiAXZmzCAHaFj&pid=Api&rs=1");
        admin.setNikename("会员");

        adminService.addAdmin(admin);
        System.err.println("管理员创建成功++");
    }

    @Test
    public void tokenTest(){
        String token = jwtUtil.createJWT("appid", "huaweiface", "wbjid");
        System.err.println(token);
    }
}