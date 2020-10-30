package com.example.demo.executer.bookCharge.controller;

import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.annonation.BaseBeforeAnnotation;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.bookCharge.model.BookChargeModel;
import com.example.demo.executer.bookCharge.service.BookChargeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "bookCharge")
public class BookChargeController {

    @Autowired
    private BookChargeService bookChargeService;

    @BaseBeforeAnnotation
    @RequestMapping("insertOne")
    public BaseResponse insertBookCharge(@RequestBody BookChargeModel model) {
        Boolean flag = bookChargeService.insertOne(model);
        if(flag){
            return new BaseResponse(ResultEnum.SUCCESS);
        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    @BaseBeforeAnnotation
    @RequestMapping("updateOne")
    public BaseResponse updateBookCharge(@RequestBody BookChargeModel model) {
        Boolean flag = bookChargeService.updateOne(model);
        if(flag){
            return new BaseResponse(ResultEnum.SUCCESS);
        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    @BaseBeforeAnnotation
    @RequestMapping("selectOne")
    public BaseResponse selectBookCharge(@RequestBody BookChargeModel model) {
        BookChargeModel bookInfoModel = bookChargeService.selectOne(model.getId());
        return new BaseResponse(ResultEnum.SUCCESS,bookInfoModel);
    }

    /**
     * @Author longtao
     * @Date   2020/10/27
     * @Describe 根据条件获取计费信息
     **/
    @BaseBeforeAnnotation
    @RequestMapping("selectBookChargeByModel")
    public BaseResponse selectBookChargeByModel(@RequestBody BookChargeModel model){
        List<BookChargeModel> bookChargeModelList =  bookChargeService.selectBookChargeByModel(model);
        return new BaseResponse(ResultEnum.SUCCESS,bookChargeModelList);
    }


}
