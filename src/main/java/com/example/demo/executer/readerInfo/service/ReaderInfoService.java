package com.example.demo.executer.readerInfo.service;

import com.example.demo.base.exception.CheckException;
import com.example.demo.base.service.BaseService;
import com.example.demo.executer.readerInfo.model.ReaderInfoModel;

import java.util.List;

public interface ReaderInfoService extends BaseService<ReaderInfoModel> {
    /**
     * @Author longtao
     * @Date   2020/10/21
     * @Describe 新建读者信息
     **/
    Boolean insertReader(ReaderInfoModel readerInfoModel);
    /**
     * @Author longtao
     * @Date   2020/10/26
     * @Describe 获取读者列表
     **/
    List<ReaderInfoModel> selectReaderInfoList(ReaderInfoModel readerInfoModel);

    /**
     * @Author longtao
     * @Date   2020/10/26
     * @Describe 判断剩余借阅量
     **/
    Boolean judgeBorrowTimes(String readerId) throws CheckException;
}
