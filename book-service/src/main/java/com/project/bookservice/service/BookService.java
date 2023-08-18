package com.project.bookservice.service;

import java.util.List;

import com.project.bookservice.dto.BookResultDTO;
import com.project.bookservice.model.Book;

public interface BookService {
    void createBook(Book book);

    Book getBook(String title);

    List<Book> getBooks();

    List<Book> getBooksDependOnAuthor(String author);

    List<Book> getBooksDependOnCategory(String bookCategory);

    BookResultDTO createNewBook(Book book, List<Long> bookCategories);

    String updateBookQuantity(String quantity, String bookid);

}
