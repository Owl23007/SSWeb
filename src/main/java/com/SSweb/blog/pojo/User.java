package com.SSweb.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Data
public class User {
    // @NotNull 这个注解表示不能为null
    @NotNull
    private Integer id;
    private String username;

    // @JsonIgnore 这个注解表示在实体类转换成json的时候,忽略这个属性
    @JsonIgnore
    private String password;

    // @NotEmpty 这个注解表示不能为空
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;
    private String email;
    private String signature;
    private String userPic;
    private String background;
    // @JsonFormat 这个注解表示在实体类转换成json的时候,将时间格式化成yyyy-MM-dd HH:mm:ss
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
