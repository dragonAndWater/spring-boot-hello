package com.example.demo.executer.readerCardInfo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.base.model.baseModel.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
@TableName("t_reader_card")
public class ReaderCardInfoModel extends BaseModel {
    /**
     * 读者id
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String readerId;
    /**
     * 可用标识（0-可用、1-黑名单、2-注销、3-挂失）
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String usableFlag;
    /**
     * 余额
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal cardAmt;
    /**
     * 创建日期
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date bindDate;
    /**
     * 备注
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String remark;

}
