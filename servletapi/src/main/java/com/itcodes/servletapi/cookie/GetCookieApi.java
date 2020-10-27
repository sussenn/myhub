package com.itcodes.servletapi.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName GetCookieApi   取出
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
@WebServlet(urlPatterns = "/cookieApi", name = "GetCookieApi")
public class GetCookieApi extends HttpServlet {
    private static final long serialVersionUID = -7843814560754865630L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)) {
                    System.out.println("username");
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
