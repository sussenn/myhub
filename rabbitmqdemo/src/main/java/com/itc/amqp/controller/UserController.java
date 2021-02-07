package com.itc.amqp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itc.amqp.pojo.User;
import com.itc.amqp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName UserController
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2021/2/5
 */
@RestController
@RequestMapping("/")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private ObjectMapper objectMapper;


    @GetMapping("/findById/{id}")
    // 无内容响应时, 设置响应码为204
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String findById(@PathVariable String id) throws JsonProcessingException {
        User user = userService.findById(id);
        return objectMapper.writeValueAsString(user);
    }
}
