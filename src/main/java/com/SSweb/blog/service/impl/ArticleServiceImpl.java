package com.SSweb.blog.service.impl;

import com.SSweb.blog.mapper.AriticleMapper;
<<<<<<< HEAD
=======
import com.SSweb.blog.pojo.Article;
>>>>>>> 6039d42a59b2fcf53d7b64779585cd6a277e41ca
import com.SSweb.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private AriticleMapper ariticleMapper;
}
