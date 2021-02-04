package com.itc.mockspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName HomeController
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2021/2/2
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
