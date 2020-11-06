package com.example.demo.executer.admin.model;

import com.example.demo.base.model.baseModel.BaseModel;
import lombok.Data;

@Data
public class AdminModel extends BaseModel {
    /**
     * 管理员姓名
     **/
    private String name;
    /**
     * 管理员职级
     **/
    private Integer rank;
    /**
     * 所属部门id
     **/
    private String departmentId;
    /**
     * 所属部门名称
     **/
    private String departmentName;
}
