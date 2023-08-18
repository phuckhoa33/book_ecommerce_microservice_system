package com.project.bookservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.bookservice.model.Category;

@Mapper
public interface CategoryMapper {
    void createCategory(Category category);

    Category getCategory(@Param("name") String name);

    List<Category> getCategories();
}
