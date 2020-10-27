package com.itcodes.servletapi.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName GetServletApi  取出
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
@WebServlet(urlPatterns = "/servletApi", name = "GetServletApi")
public class GetServletApi extends HttpServlet {
    private static final long serialVersionUID = -957966229722297420L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ServletContext
        ServletContext context = this.getServletContext();
        //取出数据
        String addr = (String) context.getAttribute("addr");

        //删除数据
        context.removeAttribute("addr");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
