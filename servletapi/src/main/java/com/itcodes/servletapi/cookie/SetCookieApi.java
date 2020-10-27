package com.itcodes.servletapi.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName SetCookieApi      存入
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
@WebServlet(urlPatterns = "/cookieApi", name = "SetCookieApi")
public class SetCookieApi extends HttpServlet {
    private static final long serialVersionUID = -7843814560754865630L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建Cookie
        Cookie cookie = new Cookie("username", "zhangsan");
        //设置有效期 cookie.setMaxAge(0);    设置为0,删除cookie
        cookie.setMaxAge(180);
        //设置有效域（名）
        //cookie.setDomain(".baidu.com");
        //发送Cookie到客户端
        response.addCookie(cookie);

        //可以向客户端发送多个Cookie，格式：Set-Cookie: age=18
        Cookie cookie1 = new Cookie("age", "18");
        response.addCookie(cookie1);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
