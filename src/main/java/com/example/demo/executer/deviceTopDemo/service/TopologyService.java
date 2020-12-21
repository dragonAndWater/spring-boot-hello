package com.example.demo.executer.deviceTopDemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.base.exception.CheckException;
import com.example.demo.executer.deviceTopDemo.model.Topology;
import com.example.demo.executer.loseBook.model.LoseBookModel;

import java.util.List;

public interface TopologyService extends IService<Topology> {
    List<Topology> getTopology(Long projectId);
}
