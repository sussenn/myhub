package com.itcodes.servletapi.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @ClassName GetSessionApi
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
@WebServlet(urlPatterns = "/sessionApi", name = "GetSessionApi")
public class GetSessionApi extends HttpServlet {
    private static final long serialVersionUID = 5252573170884763859L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println("getSession获取的sessiondid： " + session.getId());
        //获取session
        Object username = session.getAttribute("username");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
