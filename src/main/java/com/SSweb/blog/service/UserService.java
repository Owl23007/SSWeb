package com.SSweb.blog.service;

import com.SSweb.blog.pojo.User;

public interface UserService {
    User getUserByUsername(String username);

    void register(String username,String email, String password);

    void update(User user);

    void updateAvatar(String url);

    void updatePassword(String newPwd);

    void deleteAcc(Integer userId);
}
