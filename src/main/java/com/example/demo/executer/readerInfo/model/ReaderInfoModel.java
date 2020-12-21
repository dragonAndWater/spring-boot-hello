package com.example.demo.executer.readerInfo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.base.model.baseModel.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
@TableName("t_reader_info")
public class ReaderInfoModel extends BaseModel {
    /**
     * 注册日期
     **/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registerDate;
    /**
     * 借阅卡ID
     **/
    private String cardId;
    /**
     * 读者姓名
     **/
    private String readerName;
    /**
     * 读者联系电话
     **/
    private String readerPhone;
    /**
     * 读者证件类型
     **/
    private String readerCardType;
    /**
     * 读者证件号码
     **/
    private String readerCardNo;
    /**
     * 最大阅量
     **/
    private Long borrowMaxTimes;
    /**
     * 剩余借阅量
     **/
    private Long borrowUsableTimes;

    public ReaderInfoModel() {
    }
}
