package com.example.demo.executer.readerBlackList.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.annonation.BaseBeforeAnnotation;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.readerBlackList.excelListener.BlackListExcelListener;
import com.example.demo.executer.readerBlackList.model.BlackListExcel;
import com.example.demo.executer.readerBlackList.model.ReaderBlackListModel;
import com.example.demo.executer.readerBlackList.service.ReaderBlackListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequestMapping(value = "readerBlackList")
public class ReaderBlackListController {

    @Autowired
    private ReaderBlackListService readerBlackListService;

    @BaseBeforeAnnotation
    @RequestMapping("insertOne")
    public BaseResponse insertReaderBlackList(@RequestBody ReaderBlackListModel model) {
        Boolean flag = readerBlackListService.save(model);
        if (flag) {
            return new BaseResponse(ResultEnum.SUCCESS);
        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    @BaseBeforeAnnotation
    @RequestMapping("updateOne")
    public BaseResponse updateReaderBlackList(@RequestBody ReaderBlackListModel model) {
//        Boolean flag = readerBlackListService.update(model);
//        if(flag){
//            return new BaseResponse(ResultEnum.SUCCESS);
//        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    @BaseBeforeAnnotation
    @RequestMapping("selectOne")
    public BaseResponse selectReaderBlackList(@RequestBody ReaderBlackListModel model) {
//        ReaderBlackListModel bookInfoModel = readerBlackListService.selectOne(model.getId());
//        return new BaseResponse(ResultEnum.SUCCESS,bookInfoModel);
        return null;
    }

    @BaseBeforeAnnotation
    @RequestMapping("importExcel")
    public BaseResponse importExcel(MultipartFile file) {
        try {
//            String fileNam = file.getOriginalFilename();
//            log.info("fileName = {}",fileNam);
            log.info("------------importExcel start------------");
            //读取excel
            ExcelReader excelReader = EasyExcel.read(file.getInputStream(), BlackListExcel.class, new BlackListExcelListener(new BlackListExcel(), readerBlackListService)).build();
            //读取excel第一页内容
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
//            ReadSheet readSheet2 = EasyExcel.readSheet(1).build();
            excelReader.read(readSheet);
            // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
            excelReader.finish();
            log.info("------------importExcel end------------");

            return new BaseResponse(ResultEnum.SUCCESS);
        } catch (Exception e) {
            log.error("导入表异常：" + e);
            return new BaseResponse(ResultEnum.FAIL, e.getMessage());
        }

    }

}
