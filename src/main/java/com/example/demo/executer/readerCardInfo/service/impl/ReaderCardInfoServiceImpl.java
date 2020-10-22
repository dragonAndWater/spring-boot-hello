package com.example.demo.executer.readerCardInfo.service.impl;

import com.example.demo.base.service.impl.BaseServiceImpl;
import com.example.demo.executer.readerCardInfo.dao.ReaderCardInfoDao;
import com.example.demo.executer.readerCardInfo.model.ReaderCardInfoModel;
import com.example.demo.executer.readerCardInfo.service.ReaderCardInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReaderCardInfoServiceImpl extends BaseServiceImpl<ReaderCardInfoModel> implements ReaderCardInfoService {
    @Autowired
    private ReaderCardInfoDao readerCardInfoDao;
    @Override
    public Boolean insertCard(ReaderCardInfoModel readerCardInfoModel) {
        readerCardInfoModel.setId(this.getBaseId());
        return this.insertOne(readerCardInfoModel);
    }

    @Override
    public List<ReaderCardInfoModel> selectReaderCardList(ReaderCardInfoModel readerCardInfoModel) {
        return readerCardInfoDao.selectReaderCardList(readerCardInfoModel);
    }
}
