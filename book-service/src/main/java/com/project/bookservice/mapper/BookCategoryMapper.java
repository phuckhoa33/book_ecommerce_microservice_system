package com.project.bookservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.bookservice.model.BookCategory;

@Mapper
public interface BookCategoryMapper {
    void createBookCategory(BookCategory bookCategory);

    BookCategory getBookCategory(@Param("id") long id);

    List<BookCategory> getBookCategories();
}
