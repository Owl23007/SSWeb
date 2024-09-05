package com.SSweb.blog.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class PreviewArticle {

    private Integer id;
    private String title;
    @JsonIgnore
    private String content;
    private String coverImg;
    @JsonIgnore
    private String state;
    private Integer categoryId;
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonIgnore
    private LocalDateTime updateTime;
}
