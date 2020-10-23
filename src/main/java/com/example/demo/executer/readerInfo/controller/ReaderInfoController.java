package com.example.demo.executer.readerInfo.controller;

import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.annonation.BaseBeforeAnnotation;
import com.example.demo.base.annonation.CheckVisitTimesAroundAnnotation;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.readerInfo.model.ReaderInfoModel;
import com.example.demo.executer.readerInfo.service.ReaderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "readerInfo")
public class ReaderInfoController {

    @Autowired
    private ReaderInfoService readerInfoService;

    /**
     * @Author longtao
     * @Date 2020/10/21
     * @Describe 新建读者信息
     **/
    @BaseBeforeAnnotation
    @RequestMapping("insertOne")
    public BaseResponse insertReaderInfo(@RequestBody ReaderInfoModel model) {
        Boolean flag = readerInfoService.insertReader(model);
        if (flag) {
            return new BaseResponse(ResultEnum.SUCCESS);
        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    @BaseBeforeAnnotation
    @RequestMapping("updateOne")
    public BaseResponse updateReaderInfo(@RequestBody ReaderInfoModel model) {
        Boolean flag = readerInfoService.updateOne(model);
        if (flag) {
            return new BaseResponse(ResultEnum.SUCCESS);
        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    @BaseBeforeAnnotation
    @CheckVisitTimesAroundAnnotation
    @RequestMapping("selectOne")
    public BaseResponse selectReaderInfo(@RequestBody ReaderInfoModel model) {
        ReaderInfoModel bookInfoModel = readerInfoService.selectOne(model.getId());
        return new BaseResponse(ResultEnum.SUCCESS, bookInfoModel);
    }
}
