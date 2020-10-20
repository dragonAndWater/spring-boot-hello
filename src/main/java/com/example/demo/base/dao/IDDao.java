package com.example.demo.base.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IDDao {
    /**
     * 获取流水号序列
     **/
    String getId(String seq_name);
}
