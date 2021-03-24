package com.example.demo.executer.tArea.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.executer.tArea.model.TAreaModel;

import java.util.List;


public interface TAreaService extends IService<TAreaModel> {
    List getTreeArea(TAreaModel model);
    List getTreeAreaByFunction(TAreaModel model);
}
