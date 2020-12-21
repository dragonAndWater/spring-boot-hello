package com.example.demo.executer.deviceTopDemo.model;

import lombok.Data;

import java.util.List;

/**
 * @Author longtao
 * @Date 2020/11/12
 * @Describe 拓扑图对象
 **/
@Data
public class Topology {

    /**
     * 设备名
     **/
    private String name;
    /**
     *设备id
     **/
    private Long id;
    /**
     *设备id
     **/
    private Long parentId;
    /**
     * 子设备
     **/
    private List<Topology> children;
}
