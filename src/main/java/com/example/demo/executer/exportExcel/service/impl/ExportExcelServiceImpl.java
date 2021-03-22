package com.example.demo.executer.exportExcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.base.Enum.Msg;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.exportExcel.model.CustEnterpriseExcel;
import com.example.demo.executer.exportExcel.service.ExportExcelService;
import com.example.demo.util.excelUtil.EasyExcelUtil;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.excel.metadata.Sheet;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * @Author longtao
 * @Date   2021/3/15
 * @Describe 解析excel 实现类
 * excelUtil 解析excel 行和列都是从0开始的。
 **/
@Slf4j
@Service
public class ExportExcelServiceImpl implements ExportExcelService {

    @Override
    public BaseResponse analysisFirstSheet(MultipartFile file) {
        try {
            List<T> ts = EasyExcelUtil.syncReadModel(file.getInputStream(), CustEnterpriseExcel.class, 0, 4);
            List<CustEnterpriseExcel> list = JSONObject.parseArray(JSONObject.toJSONString(ts), CustEnterpriseExcel.class);
            for (CustEnterpriseExcel custEnterpriseExcel : list) {
                log.info("如果每条数据都有返回结果，那么不能使用.stream()");
                log.info(custEnterpriseExcel.toString());
            }
        }catch (IOException e){
            log.error("读取excel异常"+e);
            return new BaseResponse(Msg.ERROR,"读取excel失败。");
        }
        return new BaseResponse(Msg.SUCCESS);
    }

    @Override
    public BaseResponse analysisFirstSheet(File file) {
        try {
            List<T> ts = EasyExcelUtil.syncReadModel(file, CustEnterpriseExcel.class, 0, 4);
            List<CustEnterpriseExcel> list = JSONObject.parseArray(JSONObject.toJSONString(ts), CustEnterpriseExcel.class);
            for (CustEnterpriseExcel custEnterpriseExcel : list) {
                log.info("如果每条数据都有返回结果，那么不能使用.stream()");
                log.info(custEnterpriseExcel.toString());
            }
            writerCustExcel2(list);
        }catch (Exception e){
            log.error("读取excel异常"+e);
            return new BaseResponse(Msg.ERROR,"读取excel失败。");
        }
        return new BaseResponse(Msg.SUCCESS);
    }
    /**
     * @Author longtao
     * @Date   2021/3/15
     * @Describe 生成一个excel 文件，只有一个sheet sheet名字为:测试模板
     **/
    @Override
    public BaseResponse writerCustExcel(List<CustEnterpriseExcel> list) {
        log.info("开始写入excel");
//        String templateFileName = "E:/1.IDEA/Workspace/helloFile/writerExcel.xls";
//        String fileName = "E:/1.IDEA/Workspace/helloFile/writerExcel_test_1.xls";
//        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//        EasyExcel.write(fileName, CustEnterpriseExcel.class).withTemplate(templateFileName).sheet("公司信息1111").doWrite(list);
        String fileName = "E:/1.IDEA/Workspace/helloFile/writerExcel_test_2.xls";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, CustEnterpriseExcel.class).sheet("测试模板").doWrite(list);
        return new BaseResponse(Msg.SUCCESS);
    }
    /**
     * @Author longtao
     * @Date   2021/3/15
     * @Describe 生成一个excel文件，复制template
     * 如果 公司信息1111不存在，则在最后新增一个sheet
     * 如果 公司信息1111存在，则在存在的sheet后。空白行开始新增 *excel头和数据*
     **/
    public BaseResponse writerCustExcel2(List<CustEnterpriseExcel> list) {
        log.info("开始写入excel");
        String templateFileName = "E:/1.IDEA/Workspace/helloFile/writerExcel.xls";
        String fileName = "E:/1.IDEA/Workspace/helloFile/writerExcel_test_1.xls";
//        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, CustEnterpriseExcel.class).withTemplate(templateFileName).sheet("公司信息").doWrite(list);
        return new BaseResponse(Msg.SUCCESS);
    }
    public static void writeExcelModel(OutputStream os, List<? extends BaseRowModel> data, Class<? extends BaseRowModel> clazz, String sheetName){
        try{
            ExcelWriter writer = new ExcelWriter(os, ExcelTypeEnum.XLSX,true);
            Sheet sheet = new Sheet(1,0,clazz,sheetName,null);
            writer.write(data,sheet);
            writer.finish();
        }catch(Exception e){
            log.error(e.getMessage(), e);
        }
    }
}
