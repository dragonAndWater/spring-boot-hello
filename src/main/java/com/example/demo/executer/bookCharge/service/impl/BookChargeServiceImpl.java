package com.example.demo.executer.bookCharge.service.impl;

import com.example.demo.base.service.impl.BaseServiceImpl;
import com.example.demo.executer.bookCharge.dao.BookChargeDao;
import com.example.demo.executer.bookCharge.model.BookChargeModel;
import com.example.demo.executer.bookCharge.service.BookChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookChargeServiceImpl extends BaseServiceImpl<BookChargeModel> implements BookChargeService {

    @Autowired
    private BookChargeDao bookChargeDao;

    /**
     * @Author longtao
     * @Date 2020/10/27
     * @Describe
     **/
    @Override
    public List<BookChargeModel> selectBookChargeByModel(BookChargeModel bookChargeModel) {
        return bookChargeDao.selectBookChargeByModel(bookChargeModel);

    }
}
