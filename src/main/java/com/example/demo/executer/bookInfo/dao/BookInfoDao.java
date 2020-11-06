package com.example.demo.executer.bookInfo.dao;

import com.example.demo.base.dao.BaseDao;
import com.example.demo.executer.bookInfo.model.BookInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookInfoDao extends BaseDao<BookInfoModel> {
    List<BookInfoModel> selectBookList(BookInfoModel bookInfoModel);

    List<BookInfoModel> selectBookInfoAndBorrowInfo(BookInfoModel bookInfoModel);
}
