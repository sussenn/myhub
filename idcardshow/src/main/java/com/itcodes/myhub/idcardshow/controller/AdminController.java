package com.itcodes.myhub.idcardshow.controller;

import com.itcodes.myhub.idcardshow.pojo.dto.Result;
import com.itcodes.myhub.idcardshow.pojo.dto.StatusCode;
import com.itcodes.myhub.idcardshow.pojo.entity.Admin;
import com.itcodes.myhub.idcardshow.service.AdminService;
import com.itcodes.myhub.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AdminController   管理员登录
 * @Author sussen
 * @Version 1.0
 * @Data 2019/8/23
 */
@CrossOrigin
@RestController
@RequestMapping("/idcard")
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result loginIndex(@RequestBody Map<String,String> loginMap){

        Admin admin = adminService.login(loginMap.get("username"),loginMap.get("password"));

        if (admin != null){
            //签发token
            String token = jwtUtil.createJWT(String.valueOf(admin.getAid()), admin.getUsername(), "admin");
            HashMap<String, String> resultMap = new HashMap<>();
            resultMap.put("token",token);
            resultMap.put("username",admin.getUsername());
            resultMap.put("avatar",admin.getAvatar());
            log.info("【用户：" + admin.getUsername() + " 登录】");
            return new Result(true, StatusCode.OK,"登录成功",resultMap);
        }
        return new Result(false, StatusCode.LOGINERROR,"用户名或密码错误");
    }

}
