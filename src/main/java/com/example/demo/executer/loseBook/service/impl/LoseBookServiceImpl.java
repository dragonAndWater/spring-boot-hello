package com.example.demo.executer.loseBook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.arithmetic.BookArithmetic;
import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.exception.CheckException;
import com.example.demo.executer.bookInfo.model.BookInfoModel;
import com.example.demo.executer.bookInfo.service.BookInfoService;
import com.example.demo.executer.borrowBoook.model.BorrowBookModel;
import com.example.demo.executer.borrowBoook.service.BorrowBookService;
import com.example.demo.executer.loseBook.dao.LoseBookDao;
import com.example.demo.executer.loseBook.model.LoseBookModel;
import com.example.demo.executer.loseBook.service.LoseBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class LoseBookServiceImpl extends ServiceImpl<LoseBookDao,LoseBookModel> implements LoseBookService {

    @Autowired
    private BorrowBookService borrowBookService;

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private BookArithmetic bookArithmetic;

    /**
     * @Author longtao
     * @Date 2020/10/28
     * @Describe 根据书籍ID获取赔偿金额
     **/
    @Override
    public LoseBookModel getRealAmt(LoseBookModel loseBookModel) throws CheckException {
        //是否借阅出去
        BorrowBookModel borrowBookModel = borrowBookService.selectNowByBookId(new BorrowBookModel(loseBookModel.getBookId()));
        if (null == borrowBookModel) {
            throw new CheckException(ResultEnum.CHECK_BORROW_BOOK);
        }

        //根据bookId获取书籍信息 价格
        BookInfoModel bookInfoModel = bookInfoService.getOne(new QueryWrapper<BookInfoModel>().lambda().eq(BookInfoModel::getId,loseBookModel.getBookId()));
        //计算丢失书籍赔偿金额
        BigDecimal loseAmt = bookArithmetic.getLoseAmt(bookInfoModel.getRareFlag(), bookInfoModel.getBookPrice(), borrowBookModel.getBorrowDate(), new Date());

        loseBookModel.setBookName(bookInfoModel.getBookName());
        loseBookModel.setBookPrice(bookInfoModel.getBookPrice());
        loseBookModel.setReaderId(borrowBookModel.getReaderId());
        loseBookModel.setReaderName(borrowBookModel.getReaderName());
        loseBookModel.setRealPrice(loseAmt);

        return loseBookModel;
    }
}
