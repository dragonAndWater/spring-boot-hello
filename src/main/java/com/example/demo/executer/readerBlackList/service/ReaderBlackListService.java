package com.example.demo.executer.readerBlackList.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.base.exception.CheckException;
import com.example.demo.executer.readerBlackList.model.BlackListExcel;
import com.example.demo.executer.readerBlackList.model.ReaderBlackListModel;

public interface ReaderBlackListService extends IService<ReaderBlackListModel> {

    Boolean insertByExcelModel(BlackListExcel blackListExcel);
    //黑名单检查
    Boolean checkBlackList(String readerId) throws CheckException;
}
