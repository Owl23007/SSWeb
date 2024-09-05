package com.SSweb.blog.service;

import com.SSweb.blog.pojo.Category;

import java.util.List;

public interface CategoryService {

    void add(Category category);

    List<Category> list();

    String getCategoryById(Integer categoryId);
}
