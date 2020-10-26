package com.example.demo.executer.readerInfo.dao;

import com.example.demo.base.dao.BaseDao;
import com.example.demo.executer.readerInfo.model.ReaderInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReaderInfoDao extends BaseDao<ReaderInfoModel> {
    List<ReaderInfoModel> selectReaderInfoList(ReaderInfoModel readerInfoModel);

}
