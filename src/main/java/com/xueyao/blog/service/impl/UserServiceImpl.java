package com.xueyao.blog.service.impl;

import com.xueyao.blog.mapper.UserMapper;
import com.xueyao.blog.pojo.User;
import com.xueyao.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xueyao.blog.utils.RsaUtil;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        // 加密密码
        String RSAPassword = RsaUtil.getRSA(password);
        // 添加用户
        userMapper.add(username, RSAPassword);
    }

    @Override
    public void update(User user) {
        // 设置更新时间
        user.setUpdateTime(LocalDateTime.now());
        // 更新用户
        userMapper.update(user);
    }
}
