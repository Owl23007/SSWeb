package com.SSweb.blog.controller;


import com.SSweb.blog.pojo.Category;
import com.SSweb.blog.pojo.Result;
import com.SSweb.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Result onAdd(@Validated @RequestBody Category category){
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Category>> onList(){
        return Result.success(categoryService.list());
    }

    @GetMapping("/getCategoryById")
    public Result<String> onGetCategoryById(@RequestParam Integer categoryId){
        return Result.success(categoryService.getCategoryById(categoryId));
    }
}
