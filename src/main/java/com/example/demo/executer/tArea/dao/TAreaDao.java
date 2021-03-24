package com.example.demo.executer.tArea.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.executer.tArea.model.TAreaModel;
import com.example.demo.executer.tArea.model.TAreaVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TAreaDao extends BaseMapper<TAreaModel> {
    List<TAreaModel> getTreeArea(TAreaModel model);
    List<TAreaVo> getTreeAreaByFunction(TAreaModel model);
}
