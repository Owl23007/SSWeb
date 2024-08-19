package com.xueyao.blog.controller;


import com.xueyao.blog.pojo.Result;
import com.xueyao.blog.pojo.User;
import com.xueyao.blog.utils.JwtUtil;
import com.xueyao.blog.utils.RSA;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xueyao.blog.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result onRegister(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        // 检查用户是否已存在
        User u = userService.getUserByUsername(username);
        if(u!=null){
            return Result.error("用户名已存在");
        }
        // 注册
        userService.register(username,password);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<String> onLogin(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 检查用户是否存在
        User LoginUser = userService.getUserByUsername(username);
        if (LoginUser == null) {
            return Result.error("用户名不存在");
        }
        // 判断密码是否正确
        if (RSA.getRSA(password).equals(LoginUser.getPassword())){
            // 登陆成功返回Token
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",LoginUser.getId());
            claims.put("username",LoginUser.getUsername());
            // 生成token
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }
}
