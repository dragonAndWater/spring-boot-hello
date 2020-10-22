package com.example.demo.executer.readerCardInfo.controller;

import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.annonation.BaseBeforeAnnotation;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.readerCardInfo.model.ReaderCardInfoModel;
import com.example.demo.executer.readerCardInfo.service.ReaderCardInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "readerCardInfo")
public class ReaderCardInfoController {

    @Autowired
    private ReaderCardInfoService readerCardInfoService;

    /**
     * @Author longtao
     * @Date   2020/10/21
     * @Describe 生成借阅卡
     **/
    @BaseBeforeAnnotation
    @RequestMapping("insertOne")
    public BaseResponse insertReaderCard(@RequestBody ReaderCardInfoModel model) {
        Boolean flag = readerCardInfoService.insertCard(model);
        if(flag){
            return new BaseResponse(ResultEnum.SUCCESS);
        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    @BaseBeforeAnnotation
    @RequestMapping("updateOne")
    public BaseResponse updateReaderCard(@RequestBody ReaderCardInfoModel model) {
        Boolean flag = readerCardInfoService.updateOne(model);
        if(flag){
            return new BaseResponse(ResultEnum.SUCCESS);
        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    @BaseBeforeAnnotation
    @RequestMapping("selectOne")
    public BaseResponse selectReaderCard(@RequestBody ReaderCardInfoModel model) {
        ReaderCardInfoModel bookInfoModel = readerCardInfoService.selectOne(model.getId());
        return new BaseResponse(ResultEnum.SUCCESS,bookInfoModel);
    }

    @BaseBeforeAnnotation
    @RequestMapping("selectReaderCardList")
    public BaseResponse selectReaderCardList(@RequestBody ReaderCardInfoModel model) {
        model.setPageQuery();
        List<ReaderCardInfoModel> bookInfoModel = readerCardInfoService.selectReaderCardList(model);
        return new BaseResponse(ResultEnum.SUCCESS,bookInfoModel);
    }
}
