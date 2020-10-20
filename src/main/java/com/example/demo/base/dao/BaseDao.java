package com.example.demo.base.dao;

import org.springframework.stereotype.Component;

@Component
public interface BaseDao<T> {
    /**
     * 单条数据-新增
     **/
    Boolean insertOne(T t);
    /**
     * 单条数据-修改
     **/
    Boolean updateOne(T t);
    /**
     * 单条数据-查询
     **/
    T selectOne(String id);

}
