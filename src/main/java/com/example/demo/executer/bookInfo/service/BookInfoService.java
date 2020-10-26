package com.example.demo.executer.bookInfo.service;

import com.example.demo.base.service.BaseService;
import com.example.demo.executer.bookInfo.model.BookInfoExcelModel;
import com.example.demo.executer.bookInfo.model.BookInfoModel;

import java.util.List;

public interface BookInfoService extends BaseService<BookInfoModel> {

    List<BookInfoModel> selectBookList(BookInfoModel model);

    Boolean insertByExcel(BookInfoExcelModel bookInfoExcelModel);
}
