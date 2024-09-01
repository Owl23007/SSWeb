package com.SSweb.blog.mapper;

import com.SSweb.blog.pojo.OtherUser;
import com.SSweb.blog.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    // 注册
    @Insert("insert into user(username, password, email, create_time, update_time) values(#{username}, #{password}, #{email}, now(), now())")
    void add(String username, String email, String password);

    // 初始化用户信息
    @Update("update user set nickname = #{nickname}, signature = #{signature} where id = #{id}")
    void init(Integer id, String nickname, String signature);

    // 查询用户
    @Select("select * from user where username = #{username}")
    User getUserByUsername(String username);

    // 根据邮箱查询用户
    @Select("select * from user where email = #{email}")
    User getUserByEmail(String email);

    // 查询其他用户信息
    @Select("select * from user where id = #{userId}")
    OtherUser getOtherUserById(Integer userId);

    // 更新用户信息
    @Update("update user set nickname = #{nickname}, signature = #{signature}, update_time = #{updateTime} where id = #{id}")
    void update(User user);

    //更新用户头像
    @Update("update user set user_pic = #{url}, update_time = now() where id = #{id}")
    void updateAvatar(Integer id ,String url);

    //更新用户密码
    @Update("update user set password = #{rsaPassword}, update_time = now() where id = #{id}")
    void updatePassword(Integer id ,String rsaPassword);

    //删除用户
    @Update("delete from user where id = #{userId}")
    void deleteAcc(Integer userId);
}
