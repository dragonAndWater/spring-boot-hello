package com.example.demo.executer.admin.dao;

import com.example.demo.base.dao.BaseDao;
import com.example.demo.executer.admin.model.AdminModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminDao extends BaseDao<AdminModel> {

}
