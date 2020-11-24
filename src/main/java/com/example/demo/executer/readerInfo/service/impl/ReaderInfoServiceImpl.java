package com.example.demo.executer.readerInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.exception.CheckException;
import com.example.demo.executer.readerInfo.dao.ReaderInfoDao;
import com.example.demo.executer.readerInfo.model.ReaderInfoModel;
import com.example.demo.executer.readerInfo.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReaderInfoServiceImpl extends ServiceImpl<ReaderInfoDao,ReaderInfoModel> implements ReaderInfoService {

    @Autowired
    private ReaderInfoDao readerInfoDao;

    /**
     * @Author longtao
     * @Date 2020/10/21
     * @Describe 新建读者信息
     **/
    @Override
    public Boolean insertReader(ReaderInfoModel readerInfoModel) {
//        readerInfoModel.setId(this.getBaseId());
        return this.save(readerInfoModel);
    }

    @Override
    public List<ReaderInfoModel> selectReaderInfoList(ReaderInfoModel readerInfoModel) {
        return readerInfoDao.selectReaderInfoList(readerInfoModel);
    }

    /**
     * @Author longtao
     * @Date 2020/10/26
     * @Describe 剩余借阅量检查
     **/
    @Override
    public Boolean judgeBorrowTimes(String readerId) throws CheckException {
        ReaderInfoModel readerInfoModel = this.getOne(new QueryWrapper<ReaderInfoModel>().lambda().eq(ReaderInfoModel::getId,readerId));
        if (0 >= readerInfoModel.getBorrowUsableTimes()) {
            throw new CheckException(ResultEnum.CHECK_BORROW_USABLE_TIMES);
        }
        return true;
    }
}
