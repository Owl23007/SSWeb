package com.SSweb.blog.controller;


import com.SSweb.blog.pojo.Article;
import com.SSweb.blog.pojo.PreviewArticle;
import com.SSweb.blog.pojo.Result;
import com.SSweb.blog.service.ArticleService;
import jakarta.validation.constraints.NotNull;
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

    @GetMapping("/article")
    public Result onGetArticle(@RequestParam @NotNull Integer articleId){
        Article article = articleService.getArticleById(articleId);
        if(article == null){
            return Result.error("文章不存在");
        }
        return Result.success(article);
    }
}
