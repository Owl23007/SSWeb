package com.xueyao.blog.controller;


import com.xueyao.blog.pojo.Result;
import com.xueyao.blog.pojo.User;
import com.xueyao.blog.utils.JwtUtil;
import com.xueyao.blog.utils.RsaUtil;
import com.xueyao.blog.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.xueyao.blog.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {

    @Autowired
    private UserService userService;

    // @Pattern 验证参数的合法性
    @PostMapping("/register")
    public Result onRegister(@Pattern(regexp = "^\\S{5,16}$") String username, @Email String email, @NotNull String password){
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
    public Result<String> onLogin(@Pattern(regexp = "^\\S{5,16}$") String username, @NotNull String password) {
        // 检查用户是否存在
        User LoginUser = userService.getUserByUsername(username);
        if (LoginUser == null) {
            return Result.error("用户名不存在");
        }
        // 判断密码是否正确
        if (Objects.equals(RsaUtil.getRSA(password), LoginUser.getPassword())){
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
    public Result onUpdate(@RequestBody @Validated User user){
        // 更新用户基本信息
        userService.update(user);
        return Result.success();
    }

    // @RequestParam 将请求参数中的url封装到url中
    // @URL 验证参数的合法性
    @PatchMapping("/updateAvatar")
    public Result onUpdateAvatar(@RequestParam @URL String url){
        // 更新用户头像
        userService.updateAvatar(url);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result onUpdatePassword(@RequestBody Map<String,String> params){
        // 校验参数
        String old_pwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String re_pwd = params.get("re_pwd");
        if (!StringUtils.hasLength(old_pwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(re_pwd)){
            return Result.error("缺少参数。");
        }
        // 校验原密码是否正确
        Map<String,Object> claims = ThreadLocalUtil.get();
        User loginUser = userService.getUserByUsername((String) claims.get("username"));
        if (!RsaUtil.getRSA(old_pwd).equals(loginUser.getPassword())){
            return Result.error("原密码错误。");
        }
        // 校验两次密码是否一致
        if (!new_pwd.equals(re_pwd)){
            return Result.error("两次密码不一致。");
        }
        // 更新密码
        userService.updatePassword(new_pwd);
        return Result.success();
    }

    @DeleteMapping("/deleteAcc")
    public Result onDeleteAcc(){
        // 获取用户id
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer user_id = (Integer) map.get("id");
        // 删除用户
        userService.deleteAcc(user_id);
        return Result.success();
    }


}