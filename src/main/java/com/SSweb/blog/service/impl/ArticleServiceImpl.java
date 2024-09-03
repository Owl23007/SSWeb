package com.SSweb.blog.service.impl;

import com.SSweb.blog.mapper.AriticleMapper;
import com.SSweb.blog.pojo.Article;
import com.SSweb.blog.service.ArticleService;
import com.SSweb.blog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private AriticleMapper ariticleMapper;

    @Override
    public void add(Article article) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);
        ariticleMapper.add(article);
    }
}
