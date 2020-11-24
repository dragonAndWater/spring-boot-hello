package com.example.demo.executer.bookCharge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.executer.bookCharge.model.BookChargeModel;

import java.util.List;

public interface BookChargeService extends IService<BookChargeModel> {
    /**
     * @Author longtao
     * @Date   2020/10/27
     * @Describe 根据条件获取计费信息
     **/
    List<BookChargeModel> selectBookChargeByModel(BookChargeModel bookChargeModel);
}
