package com.example.demo.executer.bookInfo.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.executer.bookInfo.model.BookInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookInfoDao extends BaseMapper<BookInfoModel> {
    List<BookInfoModel> selectBookList(BookInfoModel bookInfoModel);

    List<BookInfoModel> selectBookInfoAndBorrowInfo(BookInfoModel bookInfoModel);

    @Select("select * from t_book_info  ${ew.customSqlSegment}")
    List<BookInfoModel> selectByAnnonation(@Param(Constants.WRAPPER) Wrapper wrapper);
}
