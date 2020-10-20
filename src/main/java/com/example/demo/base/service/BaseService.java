package com.example.demo.base.service;

public interface BaseService<T> {

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
    /**
     * 获取base_number序列的id
     **/
    String getBaseId();

}
