package com.example.demo.executer.borrowBoook.model;

import com.example.demo.base.model.baseModel.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class BorrowBookModel extends BaseModel {
    /**
     * 书籍id
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bookId;
    /**
     * 借阅卡id
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cardId;
    /**
     * 读者id
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String readerId;
    /**
     * 读者姓名
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String readerName;
    /**
     * 借阅标识（1-已归还，2-已借出）
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String borrowFlag;
    /**
     * 借阅日期
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date borrowDate;
    /**
     * 借阅时间
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date borrowTime;
    /**
     * 归还日期
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date backDate;
    /**
     * 归还时间
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date backTime;
    /**
     * 借阅收入
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal borrowAmt;
    /**
     * 书籍列表
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> bookIds;
    /**
     * 过期日期   60天前的日期，在该日期前未还书都是超期未还
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date overdueDate;


    public BorrowBookModel() {
    }

    public BorrowBookModel(String bookId) {
        this.bookId = bookId;
    }
}
