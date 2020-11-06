package com.example.demo.executer.readerBlackList.model;

import com.example.demo.base.model.baseModel.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReaderBlackListModel extends BaseModel {
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
     * 黑名单标识（0-黑名单，1-白名单）
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String blackFlag;

    public ReaderBlackListModel() {
    }

}
