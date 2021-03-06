package com.naz.PlexDownloader.controllers;

import com.naz.PlexDownloader.exceptions.RestException;
import com.naz.PlexDownloader.models.Book;
import com.naz.PlexDownloader.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.list();
    }

    @GetMapping("/exception")
    public Object testException() {

        throw new RestException("test.exception.with.arg", new Object[]{"ument"});
    }

    public BookService getBookService() {
        return bookService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}
