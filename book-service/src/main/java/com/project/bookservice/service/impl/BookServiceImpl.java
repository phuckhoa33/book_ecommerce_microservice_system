package com.project.bookservice.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bookservice.dto.BookResultDTO;
import com.project.bookservice.mapper.BookCategoryMapper;
import com.project.bookservice.mapper.BookMapper;
import com.project.bookservice.mapper.CategoryMapper;
import com.project.bookservice.model.Book;
import com.project.bookservice.model.BookCategory;
import com.project.bookservice.model.Category;
import com.project.bookservice.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    BookCategoryMapper bookCategoryMapper;

    @Override
    public void createBook(Book book) {
        bookMapper.createBook(book);
    }

    @Override
    public Book getBook(String bookid) {
        return bookMapper.getBook(bookid);
    }

    @Override
    public List<Book> getBooks() {
        return bookMapper.getBooks();
    }

    public BookResultDTO createNewBook(Book book, List<Long> bookCategories) {
        Book oldBook = getBook(String.valueOf(book.getTitle()));
        long bookid = createRandomId(10);

        // Check book is exist
        if (oldBook != null && (oldBook.getTitle().equals(book.getTitle()) || oldBook.getId() == book.getId())) {
            return new BookResultDTO(oldBook, "This book is exist");
        }

        // Check category
        List<Category> categories = categoryMapper.getCategories();

        Set<Long> categoryIds = new HashSet<>();
        for (Category category : categories) {
            categoryIds.add(category.getId());
        }

        if (checkListExistInASet(categoryIds, bookCategories) == false) {
            return new BookResultDTO(book, "Category is invalid");
        }

        // Create book

        Thread createBookThread = new Thread(() -> {
            book.setId(bookid);
            this.createBook(book);
        });
        Thread createBookCategoryThread = new Thread(() -> {
            for (Long categoryid : bookCategories) {
                BookCategory newBookCategory = new BookCategory(createRandomId(10), bookid, categoryid);
                bookCategoryMapper.createBookCategory(newBookCategory);
            }
        });

        createBookThread.start();
        createBookCategoryThread.start();
        return new BookResultDTO(book, "Create book is successfully");
    }

    @Override
    public List<Book> getBooksDependOnCategory(String bookCategory) {
        return bookMapper.getBooksDependOnCategory(bookCategory);
    }

    @Override
    public List<Book> getBooksDependOnAuthor(String author) {
        return bookMapper.getBooksDependOnAuthor(author);
    }

    @Override
    public String updateBookQuantity(String quantity, String bookid) {
        bookMapper.updateBookQuantity(quantity, bookid);

        return "Update quantity is successfully";
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

    public boolean checkListExistInASet(Set<Long> primaryObject, List<Long> neededConfirmedObject) {
        for (Long bookCategoryId : neededConfirmedObject) {
            if (!primaryObject.contains(bookCategoryId)) {
                return false;
            }
        }

        return true;
    }
}
