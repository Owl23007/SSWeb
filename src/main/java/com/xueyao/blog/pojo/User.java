package com.xueyao.blog.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

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
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
