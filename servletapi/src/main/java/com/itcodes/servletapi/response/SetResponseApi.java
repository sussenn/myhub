package com.itcodes.servletapi.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName SetResponseApi
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
@WebServlet(urlPatterns = "/responseApi", name = "SetResponseApi")
public class SetResponseApi extends HttpServlet {
    private static final long serialVersionUID = 523552838570354325L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //同时设置response编码方式和浏览器的解码方式
        response.setContentType("text/html;charset=utf-8");
        //设置响应头：
        response.setHeader("username", "zhangsan");
        //设置响应状态码
        response.setStatus(302);

        //重定向
        response.sendRedirect("/test");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
