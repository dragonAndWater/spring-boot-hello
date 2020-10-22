package com.example.demo.executer.readerCardInfo.dao;

import com.example.demo.base.dao.BaseDao;
import com.example.demo.executer.readerCardInfo.model.ReaderCardInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReaderCardInfoDao extends BaseDao<ReaderCardInfoModel> {
    List<ReaderCardInfoModel> selectReaderCardList(ReaderCardInfoModel readerCardInfoModel);
}
