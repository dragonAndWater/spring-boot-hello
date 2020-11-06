package com.example.demo.executer.loseBook.model;

import com.example.demo.base.model.baseModel.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LoseBookModel extends BaseModel {
    /**
     * 书籍id
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bookId;
    /**
     * 书籍名称
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bookName;
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
     * 书籍原价
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal bookPrice;
    /**
     * 补偿金额
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal realPrice;
    /**
     * 遗失日期
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date loseDate;

    public LoseBookModel() {
    }

}
