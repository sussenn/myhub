package com.itcodes.myhub.idcardshow.interceptor;

import com.itcodes.myhub.idcardshow.exception.SuException;
import com.itcodes.myhub.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName JwtInterceptor
 * @Author sussen
 * @Version 1.0
 * @Data 2019/11/19
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头获取token
        String token = request.getHeader(jwtUtil.getJwtHeader());
        //也可以从请求体中取出
        if (token == null){
            token = request.getParameter(jwtUtil.getJwtHeader());
        }

        //凭证为空
        if (token == null || "".equals(token)) {
            throw new SuException("token不能为空", HttpStatus.UNAUTHORIZED.value());
        }
        //解析token
        Claims claims = jwtUtil.parseJWT(token);
        if (claims == null || jwtUtil.isTokenExpired(claims.getExpiration())){
            throw new SuException("凭证已失效",HttpStatus.UNAUTHORIZED.value());
        }
        //判断用户角色
        if ("user".equals(claims.get("roles"))){
            //"user"标识
            request.setAttribute("user",claims.getId());
        }
        if ("admin".equals(claims.get("roles"))){
            //"admin"标识
            request.setAttribute("admin",claims.getId());
        }
        //最终放行
        return true;
    }
}
