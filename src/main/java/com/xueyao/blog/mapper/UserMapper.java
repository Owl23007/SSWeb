package com.xueyao.blog.mapper;

import com.xueyao.blog.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    // 注册
    @Insert("insert into user(username, password,create_time,update_time) values(#{username}, #{password},now(),now())")
    void add(String username, String password);

    // 查询用户
    @Select("select * from user where username = #{username}")
    User getUserByUsername(String username);
}
