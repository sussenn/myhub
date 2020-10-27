package com.itcodes.servletapi.response;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName GetResponseApi
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
@WebServlet(urlPatterns = "/responseApi", name = "GetResponseApi")
public class GetResponseApi extends HttpServlet {
    private static final long serialVersionUID = 2710063754431240338L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //字节型响应体设置
        ServletOutputStream os = response.getOutputStream();
        //创建输入流，读取/img/xxx.jpg
        InputStream is = this.getServletContext().getResourceAsStream("/img/xxx.jpg");
        //把数据写到输出流里
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = is.read(buffer))!=-1){
            os.write(buffer, 0 ,len);
        }
        //关闭流
        is.close();
        os.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}