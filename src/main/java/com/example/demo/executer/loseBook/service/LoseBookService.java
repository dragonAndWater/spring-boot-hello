package com.example.demo.executer.loseBook.service;

import com.example.demo.base.exception.CheckException;
import com.example.demo.base.service.BaseService;
import com.example.demo.executer.loseBook.model.LoseBookModel;

public interface LoseBookService extends BaseService<LoseBookModel> {

    /**
     * @Author longtao
     * @Date 2020/10/28
     * @Describe 根据书籍ID获取赔偿金额
     **/
    LoseBookModel getRealAmt(LoseBookModel loseBookModel) throws CheckException;
}
