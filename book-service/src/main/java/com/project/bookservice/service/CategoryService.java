package com.project.bookservice.service;

import com.project.bookservice.dto.CategoryAndBookCategoryResultDTO;
import com.project.bookservice.model.Category;

public interface CategoryService {
    String createCategory(Category category);

    Category getCategory(String name);

    CategoryAndBookCategoryResultDTO getCategories();
}
