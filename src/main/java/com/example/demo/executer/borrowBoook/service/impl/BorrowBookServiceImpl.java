package com.example.demo.executer.borrowBoook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.arithmetic.BookArithmetic;
import com.example.demo.base.Enum.BorrowFlagEnum;
import com.example.demo.base.Enum.Msg;
import com.example.demo.base.exception.CheckException;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.bookCharge.model.BookChargeModel;
import com.example.demo.executer.bookCharge.service.BookChargeService;
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

public class BorrowBookServiceImpl extends ServiceImpl<BorrowBookDao,BorrowBookModel> implements BorrowBookService {
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

    @Autowired
    private BookChargeService bookChargeService;

    @Autowired
    private BookArithmetic bookArithmetic;

    /**
     * @Author longtao
     * @Date 2020/10/26
     * @Describe 读者进行借阅
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
            borrowBookModel.setBookId(bookId);
            borrowBookModel.setBorrowFlag(BorrowFlagEnum.LEN_OUT.getCode());
            borrowBookModel.setBorrowDate(new Date());
            borrowBookModel.setBorrowTime(new Date());
            Boolean flag = this.save(borrowBookModel);

            //更新t_book_info
            BookInfoModel bookInfoModel = new BookInfoModel();
            bookInfoModel.setId(borrowBookModel.getBookId());
            bookInfoModel.setBorrowFlag(BorrowFlagEnum.LEN_OUT.getCode());

            bookInfoModel.setBorrowTimes(bookInfoService.getOne(new QueryWrapper<BookInfoModel>().lambda().eq(BookInfoModel::getId,bookInfoModel.getId())).getBorrowTimes() + 1);//书籍借阅次数+1
            bookInfoService.update(new UpdateWrapper<BookInfoModel>());

            //更新t_reader_info剩余借阅量
            ReaderInfoModel readerInfoModel = new ReaderInfoModel();
            readerInfoModel.setId(borrowBookModel.getReaderId());
            readerInfoService.update(new UpdateWrapper<ReaderInfoModel>(readerInfoModel));
            resultMap.put(bookId, flag);
        }
        return new BaseResponse(Msg.SUCCESS, resultMap);
    }

    /**
     * @Author longtao
     * @Date 2020/10/26
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
    public List<BorrowBookModel> selectOverdueBorrowList(BorrowBookModel borrowBookModel) {
        borrowBookModel.setOverdueDate(DateUtil.getBeforeMonthsTime(-overdueMonth));
        return borrowBookDao.selectOverdueBorrowList(borrowBookModel);
    }

    /**
     * @Author longtao
     * @Date 2020/10/27
     * @Describe mybatis 大于号测试
     **/
    @Override
    public List<BorrowBookModel> selectNowBorrowList(BorrowBookModel borrowBookModel) {
        borrowBookModel.setOverdueDate(DateUtil.getBeforeMonthsTime(-overdueMonth));
        return borrowBookDao.selectNowBorrowList(borrowBookModel);
    }

    /**
     * @Author longtao
     * @Date 2020/10/28
     * @Describe 获取未归还书籍的借阅费用
     **/
    @Override
    public BorrowBookModel getBorrowAmt(BorrowBookModel borrowBookModel) {
        //根据书籍ID获取 book_type
        BookInfoModel bookInfoModel = bookInfoService.getOne(new QueryWrapper<BookInfoModel>().lambda().eq(BookInfoModel::getId,borrowBookModel.getBookId()));
        //根据book_type获取计费标准
        BookChargeModel bookChargeModel = bookChargeService.getOne(new QueryWrapper<BookChargeModel>()
                .lambda()
                .eq(BookChargeModel::getBookType,bookInfoModel.getBookType()));
        //计费算法 计费类别，计费金额，借阅日期，归还日期
        borrowBookModel.setBorrowAmt(bookArithmetic.getBorrowAmt(bookChargeModel.getFeeAmt(), bookChargeModel.getFeeType(), borrowBookModel.getBorrowDate(), new Date()));
        return borrowBookModel;
    }

    /**
     * @Author longtao
     * @Date 2020/10/26
     * @Describe 根据bookId获取借阅数据 未归还的书籍
     **/
    @Override
    public BorrowBookModel selectNowByBookId(BorrowBookModel borrowBookModel) {
        return borrowBookDao.selectNowByBookId(borrowBookModel);
    }

    /**
     * @Author longtao
     * @Date 2020/10/28
     * @Describe 归还书籍
     **/
    @Override
    @Transactional
    public Map<String, Boolean> updateBorrowBook(List<BorrowBookModel> borrowBookModelList) {
        Map<String, Boolean> resultMap = new HashMap<>();
        borrowBookModelList.forEach(borrowBookModel -> {
            //根据书籍id获取借阅信息
            BorrowBookModel borrowModel = selectNowByBookId(borrowBookModel);

            //1.t_book_info
            BookInfoModel bookInfoModel = new BookInfoModel();
            bookInfoModel.setId(borrowBookModel.getBookId());
            bookInfoModel.setBorrowFlag(BorrowFlagEnum.RETURN.getCode());
            bookInfoService.update(new UpdateWrapper<BookInfoModel>());

            //2.t_reader_info
            ReaderInfoModel readerInfoModel = new ReaderInfoModel();
            readerInfoModel.setId(borrowModel.getReaderId());
//            readerInfoModel.setBorrowUsableTimes(readerInfoService.selectOne(readerInfoModel.getId()).getBorrowUsableTimes()+1);
//            readerInfoService.updateOne(readerInfoModel);

            //3.t_borrow_book借阅流水表
            borrowBookModel.setId(borrowModel.getId());
            borrowBookModel.setBackDate(new Date());
            borrowBookModel.setBackTime(new Date());
            borrowBookModel.setBorrowFlag(BorrowFlagEnum.RETURN.getCode());
//            Boolean flag = this.updateOne(borrowBookModel);

//            resultMap.put(borrowBookModel.getBookId(),flag);
        });
        return resultMap;
    }

    /**
     * @Author longtao
     * @Date 2020/10/28
     * @Describe 获取未归还书籍的借阅流水
     **/
    public Boolean judgeOverdueBorrow(String cardId) throws CheckException {
        BorrowBookModel borrowBookModel = new BorrowBookModel();
        borrowBookModel.setCardId(cardId);
        List<BorrowBookModel> borrowBookModelList = selectOverdueBorrowList(borrowBookModel);
        if (null != borrowBookModelList && borrowBookModelList.size() > 0) {
            throw new CheckException(Msg.CHECK_BORROW_OVERDUE);
        }
        return true;
    }
}
