package com.example.demo.executer.loseBook.model;

import com.example.demo.base.model.baseModel.BaseModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LoseBookModel extends BaseModel {
    /**
     * 书籍id
     **/
    private Long bookId;
    /**
     * 读者id
     **/
    private Long readerId;
    /**
     * 读者姓名
     **/
    private String readerName;
    /**
     * 书籍原价
     **/
    private BigDecimal bookPrice;
    /**
     * 补偿金额
     **/
    private BigDecimal realPrice;
    /**
     * 遗失日期
     **/
    private Date loseDate;

    public LoseBookModel() {
    }

}
