package com.example.demo.service;

import com.example.demo.dao.BookDao;
import com.example.demo.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class BookService {
    //此处使用Resource，不是Autowired
    @Resource
    BookDao bookDao;

    public Book getByName(String bookName){
        Book book = bookDao.getBook(bookName);
        log.info("book = "+ book);
        log.info("book.getBook() = "+book.getBook());
        log.info("book.getPrice() = "+ book.getPrice());
        return book;
    }

    public boolean insertBook(Book book){
        try {
//            boolean flag = bookDao.insertBook(book);
            Boolean flag = bookDao.baseInsert(book);
            log.info("flag = "+flag);
            return flag;
        }catch (Exception e){
            log.error("插入数据库失败:"+e);
            return false;
        }
    }
}
