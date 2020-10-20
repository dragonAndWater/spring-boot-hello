package com.example.demo.executer.borrowBoook.dao;

import com.example.demo.base.dao.BaseDao;
import com.example.demo.executer.borrowBoook.model.BorrowBookModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BorrowBookDao extends BaseDao<BorrowBookModel> {
}
