package com.SSweb.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

// 展示其他用户信息的实体类
// 仅展示用户名,昵称,头像,注册时间,签名,背景
@Data
public class OtherUser {
    // @JsonIgnore 这个注解表示在实体类转换成json的时候,忽略这个属性
    @JsonIgnore
    private Integer id;

    private String username;
    @JsonIgnore
    private String password;

    private String nickname;

    @JsonIgnore
    private String email;

    private String signature;
    private String userPic;
    private String background;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonIgnore
    private LocalDateTime updateTime;
}
