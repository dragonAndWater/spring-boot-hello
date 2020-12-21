package com.example.demo.executer.bookInfo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.executer.bookInfo.model.BookInfoExcelModel;
import com.example.demo.executer.bookInfo.model.BookInfoModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookInfoService extends IService<BookInfoModel> {

    List<BookInfoModel> selectBookList(BookInfoModel model);

    Boolean insertByExcel(BookInfoExcelModel bookInfoExcelModel);

    List<BookInfoModel> selectBookInfoAndBorrowInfo(BookInfoModel bookInfoModel);

    List<BookInfoModel> selectByAnnonation(@Param(Constants.WRAPPER) Wrapper wrapper);
}
