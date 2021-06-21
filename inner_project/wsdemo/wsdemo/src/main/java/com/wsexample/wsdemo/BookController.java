package com.wsexample.wsdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    private Book bookStore;
    public BookController() {
        this.bookStore = new Book("C++ programming", "VGU-public", new Author("Khoa Nguyen", 19));
    }

    @GetMapping("books")
    public Book getBooks() {
        return this.bookStore;
    }

    @PostMapping("books")
    public void insertBook(Book book) {
        System.out.println("Add new book !");
    }
}
