package com.example.demo.executer.tArea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.executer.tArea.dao.TAreaDao;
import com.example.demo.executer.tArea.model.TAreaModel;
import com.example.demo.executer.tArea.model.TAreaVo;
import com.example.demo.executer.tArea.service.TAreaService;
import com.example.demo.util.commonUtil.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TAreaServiceImpl extends ServiceImpl<TAreaDao, TAreaModel> implements TAreaService {

    @Autowired
   private TAreaDao tAreaDao;
    @Override
    public List getTreeArea(TAreaModel model) {
        return tAreaDao.getTreeArea(model);
    }

    /**
     * @Author longtao
     * @Date   2021/3/23
     * @Describe
     * 1:有参数时，返回指定省市区县tree
     * 2:无参数时，返回所有省市区县tree
     * 3:错误参数时，返回空
     **/
    @Override
    public List getTreeAreaByFunction(TAreaModel model) {
        //传入省市区的名称或区域编码
        if(StringUtil.isNotBlank(model.getAreaCode()) || StringUtil.isNotBlank(model.getAreaName())){
            QueryWrapper<TAreaModel> queryWrapper = new QueryWrapper<TAreaModel>();
            queryWrapper.lambda().eq(TAreaModel::getIsDel,0);
            if(StringUtil.isNotBlank(model.getAreaCode())){
                queryWrapper.lambda().eq(TAreaModel::getAreaCode,model.getAreaCode());
            }
            if(StringUtil.isNotBlank(model.getAreaName())){
                queryWrapper.lambda().eq(TAreaModel::getAreaName,model.getAreaName());
            }
            model =  this.getOne(queryWrapper);
            if(null == model){
                return null;
            }
        }

        //根据tArea 的id 获取改id及其所有子集（多层）
        List<TAreaVo> list = tAreaDao.getTreeAreaByFunction(model);
        //获取树型
        List<TAreaVo> result =getTree(list,model.getPid());
        return result;
    }
        public List<TAreaVo> getTree(List<TAreaVo> list, Long parentId) {
            List<TAreaVo> result = list
                    .stream()
                    .filter(node -> null == parentId ? parentId == node.getPid() : parentId.equals(node.getPid()))
                    .map(node -> {
                        node.setChildren(getTree(list, node.getId()));
                        return node;
                    })
                    .collect(Collectors.toList());
            return result;
        }
}
