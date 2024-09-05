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

    // 新增文章
    @PostMapping("/add")
    public Result onAdd(@RequestBody Article article){
        articleService.add(article);
        return Result.success();
    }

    // 删除文章
    @DeleteMapping("/delete")
    public Result onDelete(@RequestParam Integer articleId){
        if (articleService.delete(articleId))
            return Result.success();
        return Result.error("你没有权限删除这篇文章");
    }

    // 获取文章预览列表
    @GetMapping("/list")
    public Result<List<PreviewArticle>> onList(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        return Result.success(articleService.list(pageNum, pageSize));
    }

    // 获取文章详情
    @GetMapping("/article")
    public Result onGetArticle(@RequestParam @NotNull Integer articleId){
        Article article = articleService.getArticleById(articleId);
        if(article == null){
            return Result.error("文章不存在");
        }
        return Result.success(article);
    }

    // 获取用户文章预览
    @GetMapping("/userArticle")
    public Result<List<PreviewArticle>> onGetUserArticle(){
        List<PreviewArticle> articles = articleService.getUserArticle();
        return Result.success(articles);
    }
}
