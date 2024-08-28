package com.xueyao.blog.service.impl;

import com.xueyao.blog.mapper.AriticleMapper;
import com.xueyao.blog.pojo.Article;
import com.xueyao.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private AriticleMapper ariticleMapper;
}
