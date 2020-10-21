package com.example.demo.base.model.baseModel;

import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {
    /**
     * 上游系统唯一系统标识号 12位系统号
     **/
    private String upSystemId;
    /**
     * 上游系统唯一流水号 21位
     **/
    private String upSystemSeqNo;
    /**
     * 上游系统 请求日期 2020-10-20
     **/
    private Date tranDate;
    /**
     * 上游系统 操作人 12位
     **/
    private String userId;
    /**
     * 主键ID
     **/
    private String id;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 更新时间
     **/
    private Date updateTime;
}
