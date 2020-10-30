package com.example.demo.executer.bookCharge.dao;

import com.example.demo.base.dao.BaseDao;
import com.example.demo.executer.bookCharge.model.BookChargeModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookChargeDao extends BaseDao<BookChargeModel> {
    /**
     * @Author longtao
     * @Date   2020/10/27
     * @Describe 根据条件获取计费信息
     **/
    List<BookChargeModel> selectBookChargeByModel(BookChargeModel bookChargeModel);

}
