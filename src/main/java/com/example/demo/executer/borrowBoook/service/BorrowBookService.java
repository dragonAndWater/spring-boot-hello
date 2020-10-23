package com.example.demo.executer.borrowBoook.service;

import com.example.demo.base.service.BaseService;
import com.example.demo.executer.borrowBoook.model.BorrowBookModel;

import java.util.List;

public interface BorrowBookService extends BaseService<BorrowBookModel> {

    List<BorrowBookModel> selectBorrowList(BorrowBookModel borrowBookModel);

}
