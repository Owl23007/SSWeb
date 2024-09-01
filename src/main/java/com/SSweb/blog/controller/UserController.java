package com.SSweb.blog.controller;


import com.SSweb.blog.pojo.OtherUser;
import com.SSweb.blog.pojo.Result;
import com.SSweb.blog.pojo.User;
import com.SSweb.blog.utils.JwtUtil;
import com.SSweb.blog.utils.RsaUtil;
import com.SSweb.blog.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.SSweb.blog.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    // 注册
    // @Pattern 验证参数的合法性
    @PostMapping("/register")
    public Result onRegister(@Pattern(regexp = "^\\S{5,16}$") String username, @Email String email, @NotNull String password){
        // 检查用户是否已存在
        User u = userService.getUserByUsername(username);
        if(u!=null){
            return Result.error("用户名已存在");
        }
        // 检查邮箱是否已经被注册
        User user = userService.getUserByEmail(email);
        if(user!=null){
            return Result.error("邮箱已被注册");
        }
        // 注册用户
        userService.register(username, email, password);
        // 初始化用户
        userService.init(username);
        return Result.success();
    }

    // 登录
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

    // 获取用户信息
    @GetMapping("/userinfo")
    public Result<User> onUserInfo(){
        // 根据用户名查询用户
        // 从ThreadLocal中获取用户名
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User u = userService.getUserByUsername(username);
        return Result.success(u);
    }

    // 根据用户ID查询用户信息
    @GetMapping("/userinfoById")
    public Result onUserInfoById(@RequestParam Integer userId){
        // 根据用户id查询用户
        OtherUser u = userService.getOtherUserById(userId);
        if(u==null)
            return Result.error("用户不存在");
        return Result.success(u);
    }

    // 更新用户信息
    // @RequestBody 将请求体中的json数据封装到user对象中
    // @Validated 根据实体类User验证请求参数的合法性
    @PutMapping("/update")
    public Result onUpdate(@RequestParam String nickname,@RequestParam String signature){
        // 更新用户基本信息
        userService.update(nickname,signature);
        return Result.success();
    }

    // 更新头像
    // @RequestParam 将请求参数中的url封装到url中
    // @URL 验证参数的合法性
    @PatchMapping("/updateAvatar")
    public Result onUpdateAvatar(@RequestParam @URL String url){
        // 更新用户头像
        userService.updateAvatar(url);
        return Result.success();
    }

    // 更新密码
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

    // 注销账号
    @DeleteMapping("/deleteAcc")
    public Result onDeleteAcc(@NotNull String password){

        // 获取用户
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer user_id = (Integer) map.get("id");
        String user_name = (String) map.get("username");
        User deleteUser = userService.getUserByUsername(user_name);

        // 校验密码
        if (!RsaUtil.getRSA(password).equals(deleteUser.getPassword())){
            return Result.error("密码错误。");
        }
        // 删除用户
        userService.deleteAcc(user_id);
        return Result.success();
    }
}