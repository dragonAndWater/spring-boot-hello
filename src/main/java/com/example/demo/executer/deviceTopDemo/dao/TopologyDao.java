package com.example.demo.executer.deviceTopDemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.executer.deviceTopDemo.model.Topology;
import com.example.demo.executer.loseBook.model.LoseBookModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopologyDao extends BaseMapper<Topology> {
    List<Topology> getTopology(@Param("projectId") Long projectId);
}
