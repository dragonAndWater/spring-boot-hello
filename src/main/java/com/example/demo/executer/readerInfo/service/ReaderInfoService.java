package com.example.demo.executer.readerInfo.service;

import com.example.demo.base.service.BaseService;
import com.example.demo.executer.readerInfo.model.ReaderInfoModel;

public interface ReaderInfoService extends BaseService<ReaderInfoModel> {
    /**
     * @Author longtao
     * @Date   2020/10/21
     * @Describe 新建读者信息
     **/
    Boolean insertReader(ReaderInfoModel readerInfoModel);


}
