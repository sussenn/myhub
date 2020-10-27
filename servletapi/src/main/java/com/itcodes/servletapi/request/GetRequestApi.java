package com.itcodes.servletapi.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName GetRequestApi
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
@WebServlet(urlPatterns = "/requestApi", name = "GetRequestApi")
public class GetRequestApi extends HttpServlet {
    private static final long serialVersionUID = 5703920088480517087L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //编码设置
        request.setCharacterEncoding("utf-8");
        //获取请求方式
        String method = request.getMethod();
        //获取请求资源路径
        String uri = request.getRequestURI();
        //获取请求头
        String host = request.getHeader("Host");
        //接收表单参数
        String username = request.getParameter("username");
        //接收多值的参数。需要注意null的处理
        String[] ids = request.getParameterValues("ids");
        //接收所有参数
        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            String var = Arrays.toString(value);
        }

        //获取context的路径  /servltapi 即项目根目录
        String contextPath = request.getContextPath();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}