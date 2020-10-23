package com.example.demo.executer.borrowBoook.dao;

import com.example.demo.base.dao.BaseDao;
import com.example.demo.executer.borrowBoook.model.BorrowBookModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BorrowBookDao extends BaseDao<BorrowBookModel> {
    List<BorrowBookModel> selectBorrowList(BorrowBookModel borrowBookModel);
}
