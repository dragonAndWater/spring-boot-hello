package com.example.demo.executer.readerBlackList.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.base.Enum.Msg;
import com.example.demo.base.annonation.BaseBeforeAnnotation;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.readerBlackList.excelListener.BlackListExcelListener;
import com.example.demo.executer.readerBlackList.model.BlackListExcel;
import com.example.demo.executer.readerBlackList.model.ReaderBlackListModel;
import com.example.demo.executer.readerBlackList.service.ReaderBlackListService;
import com.example.demo.util.commonUtil.StringUtil;
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
    @RequestMapping("saveOrUpdate")
    public BaseResponse saveOrUpdate(@RequestBody ReaderBlackListModel model) {
        Boolean flag = readerBlackListService.saveOrUpdate(model);
        if (flag) {
            return new BaseResponse(Msg.SUCCESS);
        }
        return new BaseResponse(Msg.ERROR);
    }


    @BaseBeforeAnnotation
    @RequestMapping("selectOne")
    public BaseResponse selectReaderBlackList(@RequestBody ReaderBlackListModel model) {
        QueryWrapper<ReaderBlackListModel> queryWrapper = new QueryWrapper<>();
        if(StringUtil.isNotBlank(model.getId())){
            queryWrapper.lambda().eq(ReaderBlackListModel::getId,model.getId());
        }
        if(StringUtil.isNotBlank(model.getReaderId())){
            queryWrapper.lambda().eq(ReaderBlackListModel::getReaderId,model.getReaderId());
        }
        if(StringUtil.isNotBlank(model.getReaderName())){
            queryWrapper.lambda().like(ReaderBlackListModel::getReaderName,model.getReaderName());
        }
        if(StringUtil.isNotBlank(model.getBlackFlag())){
            queryWrapper.lambda().eq(ReaderBlackListModel::getBlackFlag,model.getBlackFlag());
        }
        ReaderBlackListModel result = readerBlackListService.getOne(queryWrapper);
        return new BaseResponse(Msg.SUCCESS,result);
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

            return new BaseResponse(Msg.SUCCESS);
        } catch (Exception e) {
            log.error("导入表异常：" + e);
            return new BaseResponse(Msg.ERROR, e.getMessage());
        }

    }

}
