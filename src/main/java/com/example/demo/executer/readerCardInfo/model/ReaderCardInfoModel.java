package com.example.demo.executer.readerCardInfo.model;

import com.example.demo.base.model.baseModel.BaseModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class ReaderCardInfoModel extends BaseModel {
    /**
     * 读者id
     **/
    private String readerId;
    /**
     * 可用标识（0-可用、1-黑名单、2-注销、3-挂失）
     **/
    private String usableFlag;
    /**
     * 余额
     **/
    private BigDecimal cardAmt;
    /**
     * 创建日期
     **/
    private Date bindDate;
    /**
     * 备注
     **/
    private String remark;

}
