package com.project.bookservice.dto;

import java.util.List;

import com.project.bookservice.model.BookCategory;
import com.project.bookservice.model.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryAndBookCategoryResultDTO {
    private List<Category> categories;
    private List<BookCategory> bookCategories;
}
