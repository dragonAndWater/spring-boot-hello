package com.example.demo.executer.tArea.contorller;

import com.example.demo.base.Enum.Msg;
import com.example.demo.base.annonation.BaseAroundAnnotation;
import com.example.demo.base.exception.CheckException;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.tArea.model.TAreaModel;
import com.example.demo.executer.tArea.service.TAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author long_tao
 * 区域编码
 */
@Slf4j
@RestController
@RequestMapping(value = "tArea")
public class TAreaController {
    @Autowired
    private TAreaService tAreaService;

    /**
     * @Author longtao
     * @Date   2021/3/23
     * @Describe 根据6位区域编码 获取区域tree型数据
     * ps: 从省级开始
     **/
    @BaseAroundAnnotation
    @PostMapping("getTreeArea")
    public List getTreeArea(@RequestBody TAreaModel model){
        //根据id，获取id和他的子集
        return tAreaService.getTreeArea(model);
    }
    /**
     * @Author longtao
     * @Date   2021/3/23
     * @Describe 根据6位区域编码 或 区域名称 获取区域tree型数据
     * ps: 可从省级、市级、区级
     **/
    @BaseAroundAnnotation
    @PostMapping("getTreeAreaByFunction")
    public BaseResponse getTreeAreaByFunction(@RequestBody TAreaModel model) throws CheckException {
        //业务检查
//        if(StringUtil.isBlank(model.getAreaCode()) && StringUtil.isBlank(model.getAreaName())){
//            throw new CheckException(Check.CHECK_CODE_OR_NAME);
//        }
        //根据id，获取id和他的子集
        List result = tAreaService.getTreeAreaByFunction(model);
        return new BaseResponse(Msg.SUCCESS,result);
    }

}


























