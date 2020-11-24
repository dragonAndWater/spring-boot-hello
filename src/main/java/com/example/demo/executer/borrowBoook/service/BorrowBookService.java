package com.example.demo.executer.borrowBoook.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.base.exception.CheckException;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.borrowBoook.model.BorrowBookModel;

import java.util.List;
import java.util.Map;

public interface BorrowBookService extends IService<BorrowBookModel> {

    /**
     * @Author longtao
     * @Date 2020/10/26
     * @Describe 借书
     * 1:检查黑名单、是否超期
     * 2:剩余借阅量检查、登记借阅流水表、更新书籍信息、更新读者信息
     * 某本书检查不通过，则全部回滚，前端重新提交。
     **/
    BaseResponse insertBorrow(BorrowBookModel model) throws CheckException;

    /**
     * @Author longtao
     * @Date 2020/10/26
     * @Describe 查询
     **/
    List<BorrowBookModel> selectBorrowList(BorrowBookModel borrowBookModel);

    /**
     * @Author longtao
     * @Date 2020/10/26
     * @Describe 返回当前卡是否存在超期书籍
     **/
    List<BorrowBookModel> selectOverdueBorrowList(BorrowBookModel borrowBookModel);

    /**
     * @Author longtao
     * @Date 2020/10/27
     * @Describe mybatis 大于号测试
     **/
    List<BorrowBookModel> selectNowBorrowList(BorrowBookModel borrowBookModel);

    /**
     * @Author longtao
     * @Date 2020/10/28
     * @Describe 获取未归还书籍的借阅费用
     **/
    BorrowBookModel getBorrowAmt(BorrowBookModel borrowBookModel);

    /**
     * @Author longtao
     * @Date 2020/10/28
     * @Describe 获取未归还书籍的借阅流水
     **/
    BorrowBookModel selectNowByBookId(BorrowBookModel borrowBookModel);

    /**
     * @Author longtao
     * @Date 2020/10/28
     * @Describe 归还书籍
     **/
    Map<String, Boolean> updateBorrowBook(List<BorrowBookModel> borrowBookModelList);
}
