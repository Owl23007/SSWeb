package com.xueyao.blog.controller;


import com.xueyao.blog.pojo.Result;
import com.xueyao.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService ArticleService;

    @PostMapping("/add")
    public Result add(String title, String content) {
        // 做检查
        // xxxx
        // 增加新的文章
        return null;
    }
}
