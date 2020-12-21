package com.example.demo.executer.deviceTopDemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.executer.deviceTopDemo.dao.TopologyDao;
import com.example.demo.executer.deviceTopDemo.model.Topology;
import com.example.demo.executer.deviceTopDemo.service.TopologyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class TopologyServiceImpl extends ServiceImpl<TopologyDao, Topology> implements TopologyService {
    @Autowired
    private TopologyDao topologyDao;

    @Override
    public List<Topology> getTopology(Long projectId) {
        return topologyDao.getTopology(projectId);
    }
}
