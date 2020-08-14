package com.example.demo.controller;

import com.example.demo.annotation.CommonAnnotation;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
public class BookController {
    @Autowired
    BookService bookService;





    @ResponseBody
    @RequestMapping("/getByname")
    public Book getByName() {
        String name = "java";
        return bookService.getByName(name);
    }

    @ResponseBody
    @RequestMapping("/insertBook")
    public boolean inserBook(){
        Book book = new Book("b++",new BigDecimal("22.50"));
        return bookService.insertBook(book);
    }

    @CommonAnnotation
    @RequestMapping("/insertNewInfo")
    public boolean insertNewInfo(@RequestBody Book book){
        log.info("book.getBook() = "+book.getBook());
        log.info("book.getPrice() = "+book.getPrice());
        return bookService.insertBook(book);
    }
}
