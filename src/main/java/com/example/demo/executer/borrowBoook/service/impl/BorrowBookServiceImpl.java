package com.example.demo.executer.borrowBoook.service.impl;

import com.example.demo.base.service.impl.BaseServiceImpl;
import com.example.demo.executer.borrowBoook.dao.BorrowBookDao;
import com.example.demo.executer.borrowBoook.model.BorrowBookModel;
import com.example.demo.executer.borrowBoook.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BorrowBookServiceImpl extends BaseServiceImpl<BorrowBookModel> implements BorrowBookService {

    @Autowired
    BorrowBookDao borrowBookDao;

    @Override
    public List<BorrowBookModel> selectBorrowList(BorrowBookModel borrowBookModel) {
        return borrowBookDao.selectBorrowList(borrowBookModel);
    }
}
