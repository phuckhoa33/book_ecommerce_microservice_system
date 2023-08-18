package com.project.bookservice.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bookservice.dto.CategoryAndBookCategoryResultDTO;
import com.project.bookservice.mapper.BookCategoryMapper;
import com.project.bookservice.mapper.CategoryMapper;
import com.project.bookservice.model.BookCategory;
import com.project.bookservice.model.Category;
import com.project.bookservice.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    BookCategoryMapper bookCategoryMapper;

    @Override
    public String createCategory(Category category) {
        Category oldCategory = categoryMapper.getCategory(category.getName());
        if (oldCategory != null && (oldCategory.getName() == category.getName())) {
            return "Category is exist";
        }
        category.setId(createRandomId(10));
        categoryMapper.createCategory(category);
        return "Create category is successfully";
    }

    @Override
    public Category getCategory(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategory'");
    }

    @Override
    public CategoryAndBookCategoryResultDTO getCategories() {
        List<Category> categories = categoryMapper.getCategories();
        List<BookCategory> bookCategories = bookCategoryMapper.getBookCategories();
        return new CategoryAndBookCategoryResultDTO(categories, bookCategories);
    }

    public long createRandomId(int desiredLength) {
        if (desiredLength <= 0) {
            throw new IllegalArgumentException("Desired length must be a positive integer.");
        }

        Random random = new Random();
        long min = (long) Math.pow(10, desiredLength - 1);
        long max = (long) Math.pow(10, desiredLength) - 1;

        return min + random.nextLong() % (max - min + 1);
    }
}
