package com.example.demo.executer.readerCardInfo.controller;

import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.Enum.UsableFlagEnum;
import com.example.demo.base.annonation.BaseBeforeAnnotation;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.readerCardInfo.model.ReaderCardInfoModel;
import com.example.demo.executer.readerCardInfo.service.ReaderCardInfoService;
import com.example.demo.executer.readerInfo.model.ReaderInfoModel;
import com.example.demo.executer.readerInfo.service.ReaderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "readerCardInfo")
public class ReaderCardInfoController {

    @Autowired
    private ReaderCardInfoService readerCardInfoService;
    @Autowired
    private ReaderInfoService readerInfoService;

    /**
     * @Author longtao
     * @Date 2020/10/21
     * @Describe 生成借阅卡
     **/
    @BaseBeforeAnnotation
    @RequestMapping("insertOne")
    public BaseResponse insertReaderCard(@RequestBody ReaderCardInfoModel model) {
        Boolean flag = readerCardInfoService.insertCard(model);
        if (flag) {
            return new BaseResponse(ResultEnum.SUCCESS);
        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    @BaseBeforeAnnotation
    @RequestMapping("updateOne")
    public BaseResponse updateReaderCard(@RequestBody ReaderCardInfoModel model) {
//        Boolean flag = readerCardInfoService.updateOne(model);
//        if(flag){
//            return new BaseResponse(ResultEnum.SUCCESS);
//        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    @BaseBeforeAnnotation
    @RequestMapping("selectOne")
    public BaseResponse selectReaderCard(@RequestBody ReaderCardInfoModel model) {
//        ReaderCardInfoModel bookInfoModel = readerCardInfoService.selectOne(model.getId());
//        return new BaseResponse(ResultEnum.SUCCESS,bookInfoModel);
        return null;
    }

    @BaseBeforeAnnotation
    @RequestMapping("selectReaderCardList")
    public BaseResponse selectReaderCardList(@RequestBody ReaderCardInfoModel model) {
        model.setPageQuery();
        List<ReaderCardInfoModel> bookInfoModel = readerCardInfoService.selectReaderCardList(model);
        return new BaseResponse(ResultEnum.SUCCESS, bookInfoModel);
    }

    /**
     * @Author longtao
     * @Date 2020/10/23
     * @Describe 为用户进行绑卡操作
     **/
    @Transactional
    @BaseBeforeAnnotation
    @RequestMapping("bindReadCard")
    public BaseResponse bindReadCard(@RequestBody ReaderCardInfoModel readerCardInfoModel) {
        if (StringUtils.isEmpty(readerCardInfoModel.getId())) {
            return new BaseResponse(ResultEnum.CHECK_ATTRIBUTE_FAIL, "借阅卡ID不能为空");
        }
        if (StringUtils.isEmpty(readerCardInfoModel.getReaderId())) {
            return new BaseResponse(ResultEnum.CHECK_ATTRIBUTE_FAIL, "读者ID不能为空");
        }
        readerCardInfoModel.setBindDate(new Date());
        readerCardInfoModel.setUsableFlag(UsableFlagEnum.BIND.getCode());
        //更新借阅卡信息
        this.updateReaderCard(readerCardInfoModel);
        //更新读者信息
        ReaderInfoModel readerInfoModel = new ReaderInfoModel();
        readerInfoModel.setId(readerCardInfoModel.getReaderId());
        readerInfoModel.setCardId(readerCardInfoModel.getId());
        readerInfoModel.setRegisterDate(new Date());
//        readerInfoService.updateOne(readerInfoModel);

        return new BaseResponse(ResultEnum.SUCCESS);
    }
}
