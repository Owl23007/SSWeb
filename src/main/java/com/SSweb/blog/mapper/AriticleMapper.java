package com.SSweb.blog.mapper;

import com.SSweb.blog.pojo.Article;
import com.SSweb.blog.pojo.PreviewArticle;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AriticleMapper {

    // 增加文章
    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            "values(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, now(), now())")
    void add(Article article);

    // 获取文章预览列表
    @Select("select * from article")
    List<PreviewArticle> list();

    // 根据文章id获取文章
    @Select("select * from article where id = #{id}")
    Article getArticleById(Integer id);

    // 根据用户id获取文章预览
    @Select("select * from article where create_user = #{id}")
    List<PreviewArticle> getArticleByUser(Integer id);

    // 根据文章id删除文章
    @Delete("delete from article where id = #{articleId}")
    void delete(Integer articleId);
}
