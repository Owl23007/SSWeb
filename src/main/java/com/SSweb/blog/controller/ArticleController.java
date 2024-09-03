package com.SSweb.blog.controller;


import com.SSweb.blog.pojo.Article;
import com.SSweb.blog.pojo.PreviewArticle;
import com.SSweb.blog.pojo.Result;
import com.SSweb.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/add")
    public Result onAdd(@RequestBody Article article){
        articleService.add(article);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<PreviewArticle>> onList(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        return Result.success(articleService.list(pageNum, pageSize));
    }
}
