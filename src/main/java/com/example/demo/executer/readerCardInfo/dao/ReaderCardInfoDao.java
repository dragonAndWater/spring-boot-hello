package com.example.demo.executer.readerCardInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.executer.readerCardInfo.model.ReaderCardInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReaderCardInfoDao extends BaseMapper<ReaderCardInfoModel> {
}
