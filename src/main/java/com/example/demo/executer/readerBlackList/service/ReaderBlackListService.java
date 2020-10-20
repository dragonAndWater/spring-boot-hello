package com.example.demo.executer.readerBlackList.service;

import com.example.demo.base.service.BaseService;
import com.example.demo.executer.readerBlackList.model.BlackListExcel;
import com.example.demo.executer.readerBlackList.model.ReaderBlackListModel;

public interface ReaderBlackListService extends BaseService<ReaderBlackListModel> {

    Boolean insertByExcelModel(BlackListExcel blackListExcel);
}
