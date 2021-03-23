package com.example.demo.executer.deviceTopDemo.model;

import lombok.Data;

import java.util.List;

/**
 * @Author longtao
 * @Date 2020/11/12
 * @Describe 拓扑图对象
 **/
@Data
public class TreeModel {

    /**
     * 名称
     **/
    private String name;
    /**
     * id
     **/
    private Long id;
    /**
     * 父级id
     **/
    private Long parentId;
    /**
     * 子级列表
     **/
    private List<TreeModel> children;

    public TreeModel(Long id, String name,Long parentId) {
        this.name = name;
        this.id = id;
        this.parentId = parentId;
    }
    public TreeModel(Long id, String name) {
        this.name = name;
        this.id = id;
        this.parentId = null;
    }
}
