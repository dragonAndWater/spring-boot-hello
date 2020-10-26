package com.example.demo.executer.borrowBoook.service.impl;

import com.example.demo.base.Enum.BorrowFlagEnum;
import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.exception.CheckException;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.base.service.impl.BaseServiceImpl;
import com.example.demo.executer.bookInfo.model.BookInfoModel;
import com.example.demo.executer.bookInfo.service.BookInfoService;
import com.example.demo.executer.borrowBoook.dao.BorrowBookDao;
import com.example.demo.executer.borrowBoook.model.BorrowBookModel;
import com.example.demo.executer.borrowBoook.service.BorrowBookService;
import com.example.demo.executer.readerBlackList.service.ReaderBlackListService;
import com.example.demo.executer.readerInfo.model.ReaderInfoModel;
import com.example.demo.executer.readerInfo.service.ReaderInfoService;
import com.example.demo.util.dateUtile.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BorrowBookServiceImpl extends BaseServiceImpl<BorrowBookModel> implements BorrowBookService {
    /**
     * 超期时间
     **/
    @Value("${borrowBook.overdue.month}")
    private Integer overdueMonth;

    @Autowired
    private BorrowBookDao borrowBookDao;

    @Autowired
    private ReaderInfoService readerInfoService;

    @Autowired
    private ReaderBlackListService readerBlackListService;

    @Autowired
    private BookInfoService bookInfoService;

    /**
     * @Author longtao
     * @Date   2020/10/26
     * @Describe  读者进行借阅
     * 1:检查黑名单、是否超期
     * 2:剩余借阅量检查、登记借阅流水表、更新书籍信息、更新读者信息
     * 某本书检查不通过，则全部回滚，前端重新提交。
     **/
    @Override
    @Transactional
    public BaseResponse insertBorrow(BorrowBookModel borrowBookModel) throws CheckException {
        //黑名单检查 reader_id
        readerBlackListService.checkBlackList(borrowBookModel.getReaderId());
        //超期检查--按照借阅卡
        judgeOverdueBorrow(borrowBookModel.getCardId());
        Map<String, Boolean> resultMap = new HashMap<>();
        for (String bookId : borrowBookModel.getBookIds()) {
            //剩余借阅量检查
            readerInfoService.judgeBorrowTimes(borrowBookModel.getReaderId());

            //登记借阅流水
            borrowBookModel.setId(this.getBaseId());
            borrowBookModel.setBookId(bookId);
            borrowBookModel.setBorrowFlag(BorrowFlagEnum.LEN_OUT.getCode());
            borrowBookModel.setBorrowDate(new Date());
            borrowBookModel.setBorrowTime(new Date());
            Boolean flag = this.insertOne(borrowBookModel);

            //更新t_book_info
            BookInfoModel bookInfoModel = new BookInfoModel();
            bookInfoModel.setId(borrowBookModel.getBookId());
            bookInfoModel.setBorrowFlag(BorrowFlagEnum.LEN_OUT.getCode());
            bookInfoModel.setBorrowTimes(bookInfoService.selectOne(bookInfoModel.getId()).getBorrowTimes()+1);//书籍借阅次数+1
            bookInfoService.updateOne(bookInfoModel);

            //更新t_reader_info剩余借阅量
            ReaderInfoModel readerInfoModel = new ReaderInfoModel();
            readerInfoModel.setId(borrowBookModel.getReaderId());
            readerInfoModel.setBorrowUsableTimes(readerInfoService.selectOne(readerInfoModel.getId()).getBorrowUsableTimes()-1);//读者剩余借阅次数-1
            readerInfoService.updateOne(readerInfoModel);
            resultMap.put(bookId, flag);
        }
        return new BaseResponse(ResultEnum.SUCCESS, resultMap);
    }
    /**
     * @Author longtao
     * @Date   2020/10/26
     * @Describe 查询
     **/
    @Override
    public List<BorrowBookModel> selectBorrowList(BorrowBookModel borrowBookModel) {
        return borrowBookDao.selectBorrowList(borrowBookModel);
    }
    /**
     * @Author longtao
     * @Date 2020/10/26
     * @Describe 返回当前卡是否存在超期书籍
     **/
    @Override
    public List<BorrowBookModel> getOverdueBorrowList(BorrowBookModel borrowBookModel) {
        borrowBookModel.setOverdueDate(DateUtil.getBeforeMonthsTime(-overdueMonth));
        return borrowBookDao.selectOverdueBorrowList(borrowBookModel);
    }
    /**
     * @Author longtao
     * @Date   2020/10/26
     * @Describe 有超期书籍未归还
     **/
    public Boolean judgeOverdueBorrow(String cardId) throws CheckException{
        BorrowBookModel borrowBookModel = new BorrowBookModel();
        borrowBookModel.setCardId(cardId);
        List<BorrowBookModel> borrowBookModelList = getOverdueBorrowList(borrowBookModel);
        if(null != borrowBookModelList){
            throw new CheckException(ResultEnum.CHECK_BORROW_OVERDUE);
        }
        return true;
    }
}
