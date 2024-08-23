package com.xueyao.blog.controller;


import com.xueyao.blog.pojo.Result;
import com.xueyao.blog.pojo.User;
import com.xueyao.blog.utils.JwtUtil;
import com.xueyao.blog.utils.RsaUtil;
import com.xueyao.blog.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.xueyao.blog.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {

    @Autowired
    private UserService userService;

    // @Pattern 验证参数的合法性
    @PostMapping("/register")
    public Result onRegister(@Pattern(regexp = "^\\S{5,16}$") String username,@Email String email, @Pattern(regexp = "^\\S{5,16}$") String password){
        // 检查用户是否已存在
        User u = userService.getUserByUsername(username);
        if(u!=null){
            return Result.error("用户名已存在");
        }
        // 注册
        userService.register(username, email, password);
        return Result.success();
    }

    // @Pattern 验证参数的合法性
    @PostMapping("/login")
    public Result<String> onLogin(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 检查用户是否存在
        User LoginUser = userService.getUserByUsername(username);
        if (LoginUser == null) {
            return Result.error("用户名不存在");
        }
        // 判断密码是否正确
        if (RsaUtil.getRSA(password).equals(LoginUser.getPassword())){
            // 登陆成功返回Token
            // 封装claims
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",LoginUser.getId());
            claims.put("username",LoginUser.getUsername());
            // 生成token
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userinfo")
    public Result<User> onUserInfo(){
        // 根据用户名查询用户
        // 从ThreadLocal中获取用户名
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User u = userService.getUserByUsername(username);
        return Result.success(u);
    }

    // @RequestBody 将请求体中的json数据封装到user对象中
    // @Validated 根据实体类User验证请求参数的合法性
    @PutMapping("/update")
    public Result<String> onUpdate(@RequestBody @Validated User user){
        // 更新用户基本信息
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result onUpdateAvatar(@RequestParam String url){
        // 更新用户头像
        userService.updateAvatar(url);
        return Result.success();
    }
}
