package com.SSweb.blog.service;

import com.SSweb.blog.pojo.Article;
import com.SSweb.blog.pojo.PreviewArticle;

import java.util.List;

public interface ArticleService {

    // 增加文章
    void add(Article article);

    // 查询文章预览信息
    List<PreviewArticle> list(Integer pageNum, Integer pageSize);

    // 根据文章id获取文章
    Article getArticleById(Integer articleId);

    // 获取用户的文章预览信息
    List<PreviewArticle> getUserArticle();

    // 删除文章
    boolean delete(Integer articleId);
}
