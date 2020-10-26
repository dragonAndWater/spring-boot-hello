package com.example.demo.executer.borrowBoook.service;

import com.example.demo.base.exception.CheckException;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.base.service.BaseService;
import com.example.demo.executer.borrowBoook.model.BorrowBookModel;

import java.util.List;

public interface BorrowBookService extends BaseService<BorrowBookModel> {

    /**
     * @Author longtao
     * @Date   2020/10/26
     * @Describe  读者进行借阅
     * 1:检查黑名单、是否超期
     * 2:剩余借阅量检查、登记借阅流水表、更新书籍信息、更新读者信息
     * 某本书检查不通过，则全部回滚，前端重新提交。
     **/
    BaseResponse insertBorrow(BorrowBookModel model) throws CheckException;

    /**
     * @Author longtao
     * @Date   2020/10/26
     * @Describe 查询
     **/
    List<BorrowBookModel> selectBorrowList(BorrowBookModel borrowBookModel);
    /**
     * @Author longtao
     * @Date 2020/10/26
     * @Describe 返回当前卡是否存在超期书籍
     **/
    List<BorrowBookModel> getOverdueBorrowList (BorrowBookModel borrowBookModel);

}
