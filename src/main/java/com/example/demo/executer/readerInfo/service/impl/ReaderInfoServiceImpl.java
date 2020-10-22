package com.example.demo.executer.readerInfo.service.impl;

import com.example.demo.base.service.impl.BaseServiceImpl;
import com.example.demo.executer.readerInfo.model.ReaderInfoModel;
import com.example.demo.executer.readerInfo.service.ReaderInfoService;
import org.springframework.stereotype.Service;


@Service
public class ReaderInfoServiceImpl extends BaseServiceImpl<ReaderInfoModel> implements ReaderInfoService {
    /**
     * @Author longtao
     * @Date   2020/10/21
     * @Describe 新建读者信息
     **/
    @Override
    public Boolean insertReader(ReaderInfoModel readerInfoModel) {
        readerInfoModel.setId(this.getBaseId());
        return this.insertOne(readerInfoModel);
    }
}
