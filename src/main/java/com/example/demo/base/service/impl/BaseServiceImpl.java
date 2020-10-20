package com.example.demo.base.service.impl;


import com.example.demo.base.dao.BaseDao;
import com.example.demo.base.dao.IDDao;
import com.example.demo.base.service.BaseService;
import com.example.demo.util.dateUtile.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    private IDDao idDao;
    @Autowired
    private BaseDao<T> baseDao;

    @Override
    public Boolean insertOne(T t) {
        return baseDao.insertOne(t);
    }

    @Override
    public Boolean updateOne(T t) {
        return baseDao.updateOne(t);
    }

    @Override
    public T selectOne(String id) {
         T t = (T)baseDao.selectOne(id);
        return t;
    }


    @Override
    public synchronized String getBaseId() {
        //获取序列号 8位日期（20201014）+6为时间（151832）+7位序列号（1000000）
        return DateUtil.getIdTime()+ idDao.getId("base_number");
    }

}
