package com.SSweb.blog.service.impl;

import com.SSweb.blog.mapper.AriticleMapper;
import com.SSweb.blog.pojo.Article;
import com.SSweb.blog.pojo.PreviewArticle;
import com.SSweb.blog.service.ArticleService;
import com.SSweb.blog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private AriticleMapper ariticleMapper;

    // 添加文章
    @Override
    public void add(Article article) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);
        ariticleMapper.add(article);
    }

    // 获取文章列表（返回预览文章）
    @Override
    public List<PreviewArticle> list(Integer pageNum, Integer pageSize) {
        List<PreviewArticle> list = ariticleMapper.list();
        // 处理分页
        int start = (pageNum - 1) * pageSize;
        int end = start + pageSize;
        if (end > list.size()) end = list.size();
        // 返回列表
        return list.subList(start, end);
    }

    // 根据Id获取文章详情
    @Override
    public Article getArticleById(Integer id) {
        return ariticleMapper.getArticleById(id);
    }

    // 获取用户的文章
    @Override
    public List<PreviewArticle> getUserArticle() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        List<PreviewArticle> list = ariticleMapper.getArticleByUser(id);
        // 获取最后四项，如果不足4项，则返回全部
        if (list.size() > 4)
            list = list.subList(list.size() - 4, list.size());
        return list;
    }

    // 删除文章
    @Override
    public boolean delete(Integer articleId) {
        // 获取用户id
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        // 根据文章id 查询文章
        Article article = ariticleMapper.getArticleById(articleId);
        if (Objects.equals(article.getCreateUser(), id)){
            ariticleMapper.delete(articleId);
            return true;
        }
        return false;
    }
}
