package com.xueyao.blog.pojo;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    private String username;
    // 这个注解表示在实体类转换成json的时候,忽略这个属性
    @JsonIgnore
    private String password;
    private String nickname;
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
