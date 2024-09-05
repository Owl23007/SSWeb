package com.SSweb.blog.service.impl;


import com.SSweb.blog.mapper.CategoryMapper;
import com.SSweb.blog.pojo.Category;
import com.SSweb.blog.service.CategoryService;
import com.SSweb.blog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        category.setCreateUser(id);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        return categoryMapper.list(id);
    }

    @Override
    public String getCategoryById(Integer categoryId) {
        Category category = categoryMapper.getCategoryById(categoryId);
        return category.getCategoryName();
    }
}
