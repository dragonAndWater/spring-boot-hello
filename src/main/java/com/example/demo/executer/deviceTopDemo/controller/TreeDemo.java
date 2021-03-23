package com.example.demo.executer.deviceTopDemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.executer.deviceTopDemo.model.Topology;
import com.example.demo.executer.deviceTopDemo.model.TreeModel;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeDemo {
    public static void main(String[] args) {
        //获取模拟数据
        List<TreeModel> list = getListModel();
        //转成SONString，方便格式化
        String str = JSONObject.toJSONString(getTree(list,null));
        System.out.println("result = "+str);
    }
    //最重要的方法
    public static  List<TreeModel> getTree(List<TreeModel> list, Long parentId) {
        List<TreeModel> result = list
                .stream()
                .filter(node -> null == parentId ? parentId == node.getParentId() : parentId.equals(node.getParentId()))
                .map(node -> {
                    node.setChildren(getTree(list, node.getId()));
                    return node;
                })
                .collect(Collectors.toList());
        return result;
    }
    //模拟数据   自身id,自身名称，父级id（可为null）,具体见TreeModel的构造方法
    public static List<TreeModel> getListModel(){
        List<TreeModel> list = new LinkedList<>();
        list.add(new TreeModel(1L,"测试1"));
        list.add(new TreeModel(2L,"测试2"));
        list.add(new TreeModel(3L,"测试3",1L));
        list.add(new TreeModel(4L,"测试4",1L));
        list.add(new TreeModel(5L,"测试5",3L));
        list.add(new TreeModel(6L,"测试6",5L));
        list.add(new TreeModel(7L,"测试7",2L));
        list.add(new TreeModel(8L,"测试8",7L));
        return list;
    }
}
