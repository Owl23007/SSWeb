package com.SSweb.blog.controller;


import com.SSweb.blog.pojo.Result;
import com.SSweb.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService ArticleService;

    @GetMapping("/list")
    public Result<String> onList() {
        return Result.success("文章数据");
    }
}
