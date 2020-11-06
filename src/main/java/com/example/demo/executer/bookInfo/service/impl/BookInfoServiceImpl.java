package com.example.demo.executer.bookInfo.service.impl;

import com.example.demo.base.Enum.BookTypeEnum;
import com.example.demo.base.Enum.RareFlagEnum;
import com.example.demo.base.service.impl.BaseServiceImpl;
import com.example.demo.executer.bookInfo.dao.BookInfoDao;
import com.example.demo.executer.bookInfo.model.BookInfoExcelModel;
import com.example.demo.executer.bookInfo.model.BookInfoModel;
import com.example.demo.executer.bookInfo.service.BookInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookInfoServiceImpl extends BaseServiceImpl<BookInfoModel> implements BookInfoService {
    @Autowired
    private BookInfoDao bookInfoDao;

    @Override
    public List<BookInfoModel> selectBookList(BookInfoModel bookInfoModel) {
        return bookInfoDao.selectBookList(bookInfoModel);
    }

    /**
     * @Author longtao
     * @Date   2020/10/14
     * @Describe 解析excel数据，插入bookInfo表
     **/
    @Override
    public Boolean insertByExcel(BookInfoExcelModel bookInfoExcelModel) {
        BookInfoModel bookInfoModel = new BookInfoModel();
        bookInfoModel.setId(this.getBaseId());
        bookInfoModel.setBookName(bookInfoExcelModel.getBookName());
        bookInfoModel.setBookPrice(bookInfoExcelModel.getBookPrice());
        bookInfoModel.setBookAuther(bookInfoExcelModel.getBookAuther());
        bookInfoModel.setBookType(BookTypeEnum.getEnumBymsg(bookInfoExcelModel.getBookType()).getCode());
        bookInfoModel.setRareFlag(RareFlagEnum.getEnumBymsg(bookInfoExcelModel.getRareFlag()).getCode());
        bookInfoModel.setPress(bookInfoExcelModel.getPress());
        bookInfoModel.setPressDate(bookInfoExcelModel.getPressDate());
        return insertOne(bookInfoModel);
    }

    @Override
    public List<BookInfoModel> selectBookInfoAndBorrowInfo(BookInfoModel bookInfoModel) {
        return bookInfoDao.selectBookInfoAndBorrowInfo(bookInfoModel);
    }

}
