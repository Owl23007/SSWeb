package com.SSweb.blog.pojo;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class Article {

    private Integer id;

    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String title;
    private String content;

    @URL
    private String coverImg;
    private String state;
    private Integer categoryId;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
