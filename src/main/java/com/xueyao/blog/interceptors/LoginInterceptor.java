package com.xueyao.blog.interceptors;

import com.xueyao.blog.utils.JwtUtil;
import com.xueyao.blog.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

//请求拦截器，返回true代表放行，返回false代表不放行
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 令牌验证

        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Duel ")) {
            token = token.substring(5); // 去掉 "Duel " 前缀
        }
        try {
            // 解析token
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 将token携带的信息存放在ThreadLocal中
            ThreadLocalUtil.set(claims);
            // 放行
            return true;
        } catch (Exception e) {
            // 拦截
            response.setStatus(401);
            return false;
        }
    }
    // 后置处理
    // 删除ThreadLocal中的数据以防止内存泄漏
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 移除ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
