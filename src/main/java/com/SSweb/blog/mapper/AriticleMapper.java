package com.SSweb.blog.mapper;

import com.SSweb.blog.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AriticleMapper {

    // 增加文章
    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time) values(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, now(), now())")
    void add(Article article);
}
