package com.example.demo.executer.borrowBoook.controller;

import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.annonation.BaseBeforeAnnotation;
import com.example.demo.base.exception.CheckException;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.borrowBoook.model.BorrowBookModel;
import com.example.demo.executer.borrowBoook.service.BorrowBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "borrowBook")
public class BorrowBookController {

    @Autowired
    private BorrowBookService borrowBookService;

    @BaseBeforeAnnotation
    @RequestMapping("insertOne")
    public BaseResponse insertBorrowBook(@RequestBody BorrowBookModel model) {
        try {
            return borrowBookService.insertBorrow(model);
        } catch (CheckException e) {
            return new BaseResponse(e);
        }

    }

    @BaseBeforeAnnotation
    @RequestMapping("updateOne")
    public BaseResponse updateBorrowBook(@RequestBody BorrowBookModel model) {
        Boolean flag = borrowBookService.updateOne(model);
        if (flag) {
            return new BaseResponse(ResultEnum.SUCCESS);
        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    @BaseBeforeAnnotation
    @RequestMapping("selectOne")
    public BaseResponse selectBorrowBook(@RequestBody BorrowBookModel model) {
        BorrowBookModel borrowBookModel = borrowBookService.selectOne(model.getId());
        return new BaseResponse(ResultEnum.SUCCESS, borrowBookModel);
    }

    /**
     * @Author longtao
     * @Date 2020/10/23
     * @Describe 根据readeId、borrow_flag、borrow_date查询借阅流水表
     **/
    @BaseBeforeAnnotation
    @RequestMapping("selectBorrowList")
    public BaseResponse selectBorrowList(@RequestBody BorrowBookModel model) {
        model.setPageQuery();
        List<BorrowBookModel> borrowBookModelList = borrowBookService.selectBorrowList(model);
        return new BaseResponse(ResultEnum.SUCCESS, borrowBookModelList);
    }

    /**
     * @Author longtao
     * @Date 2020/10/23
     * @Describe 批量插入，一个读者借阅N本书
     **/
    @BaseBeforeAnnotation
    @RequestMapping("insertBorrowList")
    public BaseResponse insertBorrowList(@RequestBody BorrowBookModel model) {
//        Map<String,Boolean> resultMap = new HashMap<>();
//        model.getBookIds().forEach((bookId)->{
//            model.setBookId(bookId);
//            model.setBorrowFlag(BorrowFlagEnum.LEN_OUT.getCode());
//            //插入借阅流水
//            Boolean flag = borrowBookService.insertBorrow(model);
//            //更新t_book_info
//
//            //更新t_reader_info剩余借阅量
//
//             borrowBookService.insertBorrow(model);
//            resultMap.put(bookId,flag);
//        });
//        if(null == resultMap){
//            return new BaseResponse(ResultEnum.FAIL);
//        }
//        return new BaseResponse(ResultEnum.SUCCESS,resultMap);
        return null;
    }

}
