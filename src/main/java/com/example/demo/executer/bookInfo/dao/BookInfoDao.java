package com.example.demo.executer.bookInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.executer.bookInfo.model.BookInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookInfoDao extends BaseMapper<BookInfoModel> {
    List<BookInfoModel> selectBookList(BookInfoModel bookInfoModel);

    List<BookInfoModel> selectBookInfoAndBorrowInfo(BookInfoModel bookInfoModel);
}
