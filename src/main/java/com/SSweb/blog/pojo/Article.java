package com.SSweb.blog.pojo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;
    private String title;
    private String content;
    private String coverImg;
    private String state;
    private String categoryId;
    private String createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
