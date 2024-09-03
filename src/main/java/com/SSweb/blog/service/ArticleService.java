package com.SSweb.blog.service;

import com.SSweb.blog.pojo.Article;
import com.SSweb.blog.pojo.PreviewArticle;

import java.util.List;

public interface ArticleService {
    void add(Article article);

    List<PreviewArticle> list(Integer pageNum, Integer pageSize);
}
