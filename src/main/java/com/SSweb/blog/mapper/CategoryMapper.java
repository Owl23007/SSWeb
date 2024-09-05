package com.SSweb.blog.mapper;

import com.SSweb.blog.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    // 新增文章分类
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) values(#{categoryName}, #{categoryAlias}, #{createUser}, now(), now())")
    void add(Category category);

    // 查询指定用户的文章分类
    @Select("select * from category where create_user = #{id}")
    List<Category> list(Integer id);

    // 根据分类id查询分类
    @Select("select * from category where id = #{categoryId}")
    Category getCategoryById(Integer categoryId);
}
