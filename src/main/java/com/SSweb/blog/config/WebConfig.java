package com.SSweb.blog.config;

import com.SSweb.blog.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// Web配置类
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 注入拦截器
    @Autowired
    private LoginInterceptor loginInterceptor;

    // 添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 将拦截器注册到容器中
        // 不拦截登陆和注册
        // 如果有其他不需要拦截的接口，可以在这里添加
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login", "/user/register");
    }

}
