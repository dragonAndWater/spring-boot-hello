package com.example.demo.executer.readerBlackList.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.exception.CheckException;
import com.example.demo.executer.readerBlackList.dao.ReaderBlackListDao;
import com.example.demo.executer.readerBlackList.model.BlackListExcel;
import com.example.demo.executer.readerBlackList.model.ReaderBlackListModel;
import com.example.demo.executer.readerBlackList.service.ReaderBlackListService;
import org.springframework.stereotype.Service;


@Service
public class ReaderBlackListServiceImpl extends ServiceImpl<ReaderBlackListDao,ReaderBlackListModel> implements ReaderBlackListService {

    @Override
    public Boolean insertByExcelModel(BlackListExcel blackListExcel) {
        ReaderBlackListModel readerBlackListModel = new ReaderBlackListModel();
        readerBlackListModel.setReaderId(blackListExcel.getReaderId());
        readerBlackListModel.setReaderName(blackListExcel.getReaderName());
        readerBlackListModel.setBlackFlag(blackListExcel.getBlackFlag());
//        readerBlackListModel.setId(this.getBaseId());
        return this.save(readerBlackListModel);
    }

    @Override
    public Boolean checkBlackList(String readerId) throws CheckException {
        ReaderBlackListModel readerBlackListModel = this.getOne(new QueryWrapper<ReaderBlackListModel>().lambda().eq(ReaderBlackListModel::getReaderId,readerId));
        if(null != readerBlackListModel){
            throw new CheckException(ResultEnum.CHECK_BLACK_LIST);
        }
        return true;
    }
}
