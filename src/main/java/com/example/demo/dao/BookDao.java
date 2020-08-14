package com.example.demo.dao;

import com.example.demo.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface BookDao extends BaseDao<Book> {
    Book getBook(String bookName);

    BigDecimal getPrice(String bookname);

    boolean insertBook(Book book);

    List<Book> getBookList();
}
