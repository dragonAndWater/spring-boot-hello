package com.example.demo.executer.deviceTopDemo.controller;

import com.example.demo.base.Enum.Msg;
import com.example.demo.base.annonation.BaseBeforeAnnotation;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.deviceTopDemo.model.Topology;
import com.example.demo.executer.deviceTopDemo.service.TopologyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "topology")
public class TopologyController {

    @Autowired
    private TopologyService topologyService;

    @BaseBeforeAnnotation
    @RequestMapping("getTopology")
    public BaseResponse getTopology() {
        Long projectId = 1338676803065188354L;
        List<Topology> list = topologyService.getTopology(projectId);
        List<Topology> getTree = getTree(list, null);
        return new BaseResponse(Msg.SUCCESS, getTree);
    }

    public List<Topology> getTree(List<Topology> list, Long parentId) {
        List<Topology> result = list
                .stream()
                .filter(node -> null == parentId ? parentId == node.getParentId() : parentId.equals(node.getParentId()))
                .map(node -> {
                    node.setChildren(getTree(list, node.getId()));
                    return node;
                })
                .collect(Collectors.toList());
        return result;
    }

}
