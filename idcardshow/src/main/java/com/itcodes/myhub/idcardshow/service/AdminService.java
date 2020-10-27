package com.itcodes.myhub.idcardshow.service;

import com.itcodes.myhub.idcardshow.dao.AdminDao;
import com.itcodes.myhub.idcardshow.pojo.entity.Admin;
import com.itcodes.myhub.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ClassName AdminService  管理员登录业务层
 * @Author sussen
 * @Version 1.0
 * @Data 2019/8/23
 */
@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IdWorker idWorker;

    /*登录*/
    public Admin login(String username, String password) {

        Admin admin = adminDao.findByUsername(username);

        if (admin != null && bCryptPasswordEncoder.matches(password,admin.getPassword())){
            return admin;
        }
        return null;
    }

    /*新增管理员*/
    public void addAdmin(Admin admin){
        admin.setAid(idWorker.nextId());
        admin.setStatus("0");
        //bCryptPasswordEncoder
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));   //加密
        adminDao.save(admin);
    }
}
