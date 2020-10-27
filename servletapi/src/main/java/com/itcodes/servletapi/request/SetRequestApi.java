package com.itcodes.servletapi.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName SetRequestApi
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
@WebServlet(urlPatterns = "/requestApi", name = "SetRequestApi")
public class SetRequestApi extends HttpServlet {
    private static final long serialVersionUID = 2338694008173473824L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //向request域对象中保存一个数据
        request.setAttribute("error","用户名或密码错误");
        //Object error = request.getAttribute("error");

        //请求转发 到/test路径
        //[重定向在 response]
        request.getRequestDispatcher("/test").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}