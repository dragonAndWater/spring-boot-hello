package com.example.demo.executer.readerBlackList.service.impl;

import com.example.demo.base.service.impl.BaseServiceImpl;
import com.example.demo.executer.readerBlackList.model.BlackListExcel;
import com.example.demo.executer.readerBlackList.model.ReaderBlackListModel;
import com.example.demo.executer.readerBlackList.service.ReaderBlackListService;
import org.springframework.stereotype.Service;


@Service
public class ReaderBlackListServiceImpl extends BaseServiceImpl<ReaderBlackListModel> implements ReaderBlackListService {

    @Override
    public Boolean insertByExcelModel(BlackListExcel blackListExcel) {
        ReaderBlackListModel readerBlackListModel = new ReaderBlackListModel();
        readerBlackListModel.setReaderId(blackListExcel.getReaderId());
        readerBlackListModel.setReaderName(blackListExcel.getReaderName());
        readerBlackListModel.setBlackFlag(blackListExcel.getBlackFlag());
        readerBlackListModel.setId(this.getBaseId());
        return this.insertOne(readerBlackListModel);
    }
}
