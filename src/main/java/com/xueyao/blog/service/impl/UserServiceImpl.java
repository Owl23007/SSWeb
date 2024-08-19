package com.xueyao.blog.service.impl;

import com.xueyao.blog.mapper.UserMapper;
import com.xueyao.blog.pojo.User;
import com.xueyao.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xueyao.blog.utils.RSA;

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
        String RSAPassword = RSA.getRSA(password);
        // 添加用户
        userMapper.add(username, RSAPassword);
    }
}