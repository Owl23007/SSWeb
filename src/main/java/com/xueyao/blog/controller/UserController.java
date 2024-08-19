package com.xueyao.blog.controller;


import com.xueyao.blog.pojo.Result;
import com.xueyao.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xueyao.blog.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result onRegister(String username, String password){
        // 检查用户是否已存在
        User u = userService.getUserByUsername(username);
        if(u!=null){
            return Result.error("用户名已存在");
        }
        // 注册
        userService.register(username,password);
        return Result.success();
    }
}
