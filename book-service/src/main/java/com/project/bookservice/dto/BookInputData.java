package com.project.bookservice.dto;

import java.util.List;

import com.project.bookservice.model.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookInputData {
    private Book book;
    private List<Long> bookCategories;
}
