package com.xueyao.blog.service.impl;

import com.xueyao.blog.mapper.UserMapper;
import com.xueyao.blog.pojo.User;
import com.xueyao.blog.service.UserService;
import com.xueyao.blog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xueyao.blog.utils.RsaUtil;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public void register(String username, String email, String password) {
        // 加密密码
        String RSAPassword = RsaUtil.getRSA(password);
        // 添加用户
        userMapper.add(username, email, RSAPassword);
    }

    @Override
    public void update(User user) {
        // 设置更新时间
        user.setUpdateTime(LocalDateTime.now());
        // 更新用户基本信息
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String url) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer user_id = (Integer) map.get("id");
        userMapper.updateAvatar(user_id, url);
    }
}
