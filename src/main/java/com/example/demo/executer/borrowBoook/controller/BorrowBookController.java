package com.example.demo.executer.borrowBoook.controller;

import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.annonation.BaseBeforeAnnotation;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.borrowBoook.model.BorrowBookModel;
import com.example.demo.executer.borrowBoook.service.BorrowBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "borrowBook")
public class BorrowBookController {

    @Autowired
    private BorrowBookService borrowBookService;

    @BaseBeforeAnnotation
    @RequestMapping("insertOne")
    public BaseResponse insertBorrowBook(@RequestBody BorrowBookModel model) {
        Boolean flag = borrowBookService.insertOne(model);
        if(flag){
            return new BaseResponse(ResultEnum.SUCCESS);
        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    @BaseBeforeAnnotation
    @RequestMapping("updateOne")
    public BaseResponse updateBorrowBook(@RequestBody BorrowBookModel model) {
        Boolean flag = borrowBookService.updateOne(model);
        if(flag){
            return new BaseResponse(ResultEnum.SUCCESS);
        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    @BaseBeforeAnnotation
    @RequestMapping("selectOne")
    public BaseResponse selectBorrowBook(@RequestBody BorrowBookModel model) {
        BorrowBookModel borrowBookModel = borrowBookService.selectOne(model.getId());
        return new BaseResponse(ResultEnum.SUCCESS,borrowBookModel);
    }
}
