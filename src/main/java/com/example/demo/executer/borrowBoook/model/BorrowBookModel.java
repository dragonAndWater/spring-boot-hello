package com.example.demo.executer.borrowBoook.model;

import com.example.demo.base.model.baseModel.BaseModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class BorrowBookModel extends BaseModel {
    /**
     * 书籍id
     **/
    private String bookId;
    /**
     * 借阅卡id
     **/
    private String cradId;
    /**
     * 读者id
     **/
    private String readerId;
    /**
     * 读者姓名
     **/
    private String readerName;
    /**
     * 借阅标识（1-已归还，2-已借出）
     **/
    private String borrowFlag;
    /**
     * 借阅日期
     **/
    private Date borrowDate;
    /**
     * 借阅时间
     **/
    private Date borrowTime;
    /**
     * 归还日期
     **/
    private Date backDate;
    /**
     * 归还时间
     **/
    private Date backTime;
    /**
     * 借阅收入
     **/
    private BigDecimal borrowAmt;
    /**
     * 书籍列表
     **/
    private List<String> bookIds;

    public BorrowBookModel() {
    }

}
