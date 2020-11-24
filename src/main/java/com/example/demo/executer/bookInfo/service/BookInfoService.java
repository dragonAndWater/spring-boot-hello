package com.example.demo.executer.bookInfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.executer.bookInfo.model.BookInfoExcelModel;
import com.example.demo.executer.bookInfo.model.BookInfoModel;

import java.util.List;

public interface BookInfoService extends IService<BookInfoModel> {

    List<BookInfoModel> selectBookList(BookInfoModel model);

    Boolean insertByExcel(BookInfoExcelModel bookInfoExcelModel);

    List<BookInfoModel> selectBookInfoAndBorrowInfo (BookInfoModel bookInfoModel);
}
