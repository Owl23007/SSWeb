package com.SSweb.blog.service.impl;

import com.SSweb.blog.mapper.AriticleMapper;
import com.SSweb.blog.pojo.Article;
import com.SSweb.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private AriticleMapper ariticleMapper;
}
