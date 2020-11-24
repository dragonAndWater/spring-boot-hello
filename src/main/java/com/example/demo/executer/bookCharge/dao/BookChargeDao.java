package com.example.demo.executer.bookCharge.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.executer.bookCharge.model.BookChargeModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookChargeDao extends BaseMapper<BookChargeModel> {
    /**
     * @Author longtao
     * @Date   2020/10/27
     * @Describe 根据条件获取计费信息
     **/
    List<BookChargeModel> selectBookChargeByModel(BookChargeModel bookChargeModel);

}
