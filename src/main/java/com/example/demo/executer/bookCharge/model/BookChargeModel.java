package com.example.demo.executer.bookCharge.model;

import com.example.demo.base.model.baseModel.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookChargeModel extends BaseModel {
    /**
     * 书籍类别
     **/
    private String bookType;
    /**
     * 计费金额
     **/
    private BigDecimal feeAmt;
    /**
     * 计费类别（1-按天收费,<=2天，不收费。最多按60天计算）书籍外借最多60天
     **/
    private String feeType;

    public BookChargeModel() {
    }

    public BookChargeModel(String bookType) {
        this.bookType = bookType;
    }
}
