package com.SSweb.blog.service.impl;

import com.SSweb.blog.mapper.UserMapper;
import com.SSweb.blog.pojo.OtherUser;
import com.SSweb.blog.pojo.User;
import com.SSweb.blog.service.UserService;
import com.SSweb.blog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SSweb.blog.utils.RsaUtil;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        // 获取用户对象
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
        // 获取用户id
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer user_id = (Integer) map.get("id");
        // 更新用户头像
        userMapper.updateAvatar(user_id, url);
    }

    @Override
    public void updatePassword(String newPwd) {
        // 加密密码
        String RSAPassword = RsaUtil.getRSA(newPwd);
        // 获取用户id
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer user_id = (Integer) map.get("id");
        // 更新用户密码
        userMapper.updatePassword(user_id, RSAPassword);
    }

    @Override
    public void deleteAcc(Integer userId) {
        // 删除用户
        userMapper.deleteAcc(userId);
    }

    @Override
    public OtherUser getOtherUserById(Integer userId) {
        // 获取其他用户对象
        return userMapper.getOtherUserById(userId);
    }
}
