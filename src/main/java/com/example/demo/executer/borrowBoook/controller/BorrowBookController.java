package com.example.demo.executer.borrowBoook.controller;

import com.example.demo.base.Enum.Msg;
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
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "borrowBook")
public class BorrowBookController {

    @Autowired
    private BorrowBookService borrowBookService;

    /**
     * @Author longtao
     * @Date   2020/10/28
     * @Describe 借书  一个读者N本书
     **/
    @BaseBeforeAnnotation
    @RequestMapping("insertOne")
    public BaseResponse insertBorrowBook(@RequestBody BorrowBookModel model) {
        try {
            return borrowBookService.insertBorrow(model);
        } catch (CheckException e) {
            return new BaseResponse(e);
        }

    }
    /**
     * @Author longtao
     * @Date   2020/10/28
     * @Describe 还书  还书列表
     * 业务逻辑：
     * 1:扫描要换对象列表，获取每一本书的借阅金额
     * 2:传入BorrowBookModelList
     * 3:返回每本书的还款结果
     **/
    @BaseBeforeAnnotation
    @RequestMapping("updateOne")
    public BaseResponse updateBorrowBook(@RequestBody List<BorrowBookModel> modelList) {
        Map<String, Boolean> resultMap =  borrowBookService.updateBorrowBook(modelList);
        return new BaseResponse(Msg.SUCCESS, resultMap);

    }

    @BaseBeforeAnnotation
    @RequestMapping("selectOne")
    public BaseResponse selectBorrowBook(@RequestBody BorrowBookModel model) {
//        BorrowBookModel borrowBookModel = borrowBookService.selectOne(model.getBookId());
//        return new BaseResponse(ResultEnum.SUCCESS, borrowBookModel);
        return null;
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
        return new BaseResponse(Msg.SUCCESS, borrowBookModelList);
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

    /**
     * @Author longtao
     * @Date 2020/10/27
     * @Describe 获取超期书籍列表   可跟查询全部以及指定cardid
     **/
    @BaseBeforeAnnotation
    @RequestMapping("selectOverdueBorrowList")
    public BaseResponse selectOverdueBorrowList(@RequestBody BorrowBookModel model) {
        List<BorrowBookModel> borrowBookModelList = borrowBookService.selectOverdueBorrowList(model);
        return new BaseResponse(Msg.SUCCESS, borrowBookModelList);
    }

    /**
     * @Author longtao
     * @Date 2020/10/27
     * @Describe 获取未超期数据列表   可跟查询全部以及指定cardid
     **/
    @BaseBeforeAnnotation
    @RequestMapping("selectNowBorrowList")
    public BaseResponse selectNowBorrowList(@RequestBody BorrowBookModel model) {
        List<BorrowBookModel> borrowBookModelList = borrowBookService.selectNowBorrowList(model);
        return new BaseResponse(Msg.SUCCESS, borrowBookModelList);
    }


//    /**
//     * @Author longtao
//     * @Date 2020/10/27
//     * @Describe 还书
//     * 根据书籍id获取当前数据的读者 & 借阅卡信息。更新t_book_info,t_reader_info,t_reader_card
//     **/
//    public BaseResponse backBorrowBook(@RequestBody BorrowBookModel model){
//        //检查借阅表 获取全量借阅信息
//        BorrowBookModel borrowmodel = borrowBookService.selectNowByBookId(model);
//        if (null == borrowmodel) {
//            return new BaseResponse(ResultEnum.CHECK_BORROW_BOOK);
//        }
//
//    }


    /**
     * @Author longtao
     * @Date 2020/10/27
     * @Describe 根据书籍id获取书籍借阅费
     **/
    @BaseBeforeAnnotation
    @RequestMapping("getBorrowAmt")
    public BaseResponse getBorrowAmt(@RequestBody BorrowBookModel model) {
        BorrowBookModel borrowmodel = borrowBookService.selectNowByBookId(model);
        if (null == borrowmodel) {
            return new BaseResponse(Msg.CHECK_BORROW_BOOK);
        }
        BorrowBookModel borrowBookModel = borrowBookService.getBorrowAmt(borrowmodel);
        return new BaseResponse(Msg.SUCCESS, borrowBookModel.getBorrowAmt());
    }
}
