package com.SSweb.blog.service;

import com.SSweb.blog.pojo.OtherUser;
import com.SSweb.blog.pojo.User;
import jakarta.validation.constraints.Email;

public interface UserService {
    User getUserByUsername(String username);

    void register(String username,String email, String password);

    void update(String nickname,String signature);

    void init(String username);

    void updateAvatar(String url);

    void updatePassword(String newPwd);

    void deleteAcc(Integer userId);

    OtherUser getOtherUserById(Integer userId);

    User getUserByEmail(@Email String email);
}
