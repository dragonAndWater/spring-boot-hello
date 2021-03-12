package com.example.demo.executer.bookCharge.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.base.Enum.Msg;
import com.example.demo.base.annonation.BaseBeforeAnnotation;
import com.example.demo.base.model.baseModel.BaseModel;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.bookCharge.model.BookChargeModel;
import com.example.demo.executer.bookCharge.service.BookChargeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
        bookChargeService.saveOrUpdate(model);
        return new BaseResponse(Msg.ERROR);
    }

    @BaseBeforeAnnotation
    @RequestMapping("updateOne")
    public BaseResponse updateBookCharge(@RequestBody BookChargeModel model) {
        bookChargeService.saveOrUpdate(model);
        return new BaseResponse(Msg.ERROR);
    }

    @BaseBeforeAnnotation
    @RequestMapping("selectOne")
    public BaseResponse selectBookCharge(@RequestBody BookChargeModel model) {
        //拼接动态查询sql
        QueryWrapper<BookChargeModel> queryWrapper = new QueryWrapper<BookChargeModel>();
        if(!StringUtils.isEmpty(model.getId())){
            queryWrapper.lambda().eq(BaseModel::getId,model.getId());
        }
        if(!StringUtils.isEmpty(model.getBookType())){
            queryWrapper.lambda().eq(BookChargeModel::getBookType,model.getBookType());
        }
        BookChargeModel result = bookChargeService.getOne(queryWrapper);
        return new BaseResponse(Msg.SUCCESS,result);
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
        return new BaseResponse(Msg.SUCCESS,bookChargeModelList);
    }

    @BaseBeforeAnnotation
    @RequestMapping("selectList")
    public BaseResponse selectList(@RequestBody BookChargeModel model){
        //判断是否需要用到分页
        if(!StringUtils.isEmpty(model.getPageNo())){
            PageHelper.startPage(model.getPageNo(),model.getPageSize());
        }
        //mybatis-plus拼接动态sql
        QueryWrapper<BookChargeModel> queryWrapper = new QueryWrapper<BookChargeModel>();
        if(!StringUtils.isEmpty(model.getBookType())){
            queryWrapper.lambda().eq(BookChargeModel::getBookType,model.getBookType());
        }
        if(!StringUtils.isEmpty(model.getFeeType())){
            queryWrapper.lambda().eq(BookChargeModel::getFeeType,model.getFeeType());
        }
        //列表查询
        List<BookChargeModel> result = bookChargeService.list(queryWrapper);
        //获取result分页信息
        PageInfo pageInfo = new PageInfo(result);
        return new BaseResponse(Msg.SUCCESS,pageInfo.getTotal(), pageInfo.getList());
    }


}
