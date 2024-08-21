package com.xueyao.blog.service;

import com.xueyao.blog.pojo.User;

public interface UserService {
    User getUserByUsername(String username);

    void register(String username, String password);

    void update(User user);
}
