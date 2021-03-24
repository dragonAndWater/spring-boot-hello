package com.example.demo.executer.tArea.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.base.model.baseModel.BaseModel;
import lombok.Data;

import java.util.List;

@Data
@TableName("t_area")
public class TAreaModel extends BaseModel {
    /**
     * 上级地区ID
     **/
    private Long pid;
    /**
     * 上级地区IDS
     **/
    private String pIds;
    /**
     * 上级地区names
     **/
    private String pNames;
    /**
     * 地区编码
     **/
    private String areaCode;
    /**
     * 地区名
     **/
    private String areaName;
    /**
     * 展示排序
     **/
    private Integer sort;
    /**
     * 地区级别：1:省份province，2:市city，3:区县district，4:街道/乡street，5：社区/村
     **/
    private String level;
    /**
     * 城市区号：长途电话区号
     **/
    private String cityCode;
    /**
     * 城市中心点：经纬度坐标
     **/
    private String center;
    /**
     * 删除标记：0未删除，1删除
     **/
    private Integer isDel;
    @TableField(exist=false)
    private List<TAreaModel> children;
}
