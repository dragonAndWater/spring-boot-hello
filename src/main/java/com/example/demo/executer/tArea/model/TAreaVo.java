package com.example.demo.executer.tArea.model;

import lombok.Data;

import java.util.List;

@Data
public class TAreaVo {
    /**
     * ID
     **/
    private Long id;
    /**
     * 上级ID
     **/
    private Long pid;
    /**
     * 地区编码
     **/
    private String areaCode;
    /**
     * 地区名
     **/
    private String areaName;
    /**
     * 子集
     **/
    private List<TAreaVo> children;


}
