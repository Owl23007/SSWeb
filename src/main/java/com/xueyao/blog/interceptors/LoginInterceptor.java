package com.xueyao.blog.interceptors;

import com.xueyao.blog.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

//请求拦截器，返回true代表放行，返回false代表不放行
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) {
        // 令牌验证
        String token = request.getHeader("Authorization");
        try{
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 放行
            return true;
        } catch (Exception e) {
            // 拦截
            response.setStatus(401);
            return false;
        }
    }
}
