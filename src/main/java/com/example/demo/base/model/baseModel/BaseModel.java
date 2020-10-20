package com.example.demo.base.model.baseModel;

import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {
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
