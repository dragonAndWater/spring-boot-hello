package com.example.demo.executer.bookCharge.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.base.model.baseModel.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("t_book_charge")
public class BookChargeModel extends BaseModel {
    /**
     * 书籍类别
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bookType;
    /**
     * 计费金额
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal feeAmt;
    /**
     * 计费类别（1-按天收费,<=2天，不收费。最多按60天计算）书籍外借最多60天
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String feeType;

    public BookChargeModel() {
    }

    public BookChargeModel(String bookType) {
        this.bookType = bookType;
    }
}
