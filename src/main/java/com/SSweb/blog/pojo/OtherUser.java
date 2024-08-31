package com.SSweb.blog.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

// 展示其他用户信息的实体类
// 仅展示用户名,昵称,头像,注册时间
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

    private String userPic;
    private LocalDateTime createTime;

    @JsonIgnore
    private LocalDateTime updateTime;
}
