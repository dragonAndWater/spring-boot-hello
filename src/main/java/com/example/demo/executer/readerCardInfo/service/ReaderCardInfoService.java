package com.example.demo.executer.readerCardInfo.service;

import com.example.demo.base.service.BaseService;
import com.example.demo.executer.readerCardInfo.model.ReaderCardInfoModel;

import java.util.List;

public interface ReaderCardInfoService extends BaseService<ReaderCardInfoModel> {
    /**
     * 单条数据-新增
     **/
    Boolean insertCard(ReaderCardInfoModel readerCardInfoModel);

    /**
     * @Author longtao
     * @Date   2020/10/21
     * @Describe 批量查询借阅卡信息
     **/
    List<ReaderCardInfoModel> selectReaderCardList(ReaderCardInfoModel readerCardInfoModel);




}
